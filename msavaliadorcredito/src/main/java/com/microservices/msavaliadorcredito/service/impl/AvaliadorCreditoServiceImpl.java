package com.microservices.msavaliadorcredito.service.impl;

import com.microservices.msavaliadorcredito.domain.model.*;
import com.microservices.msavaliadorcredito.exception.DadosClienteNotFoundException;
import com.microservices.msavaliadorcredito.exception.ErroComunicacaoMicroservicesException;
import com.microservices.msavaliadorcredito.proxy.CartoesProxy;
import com.microservices.msavaliadorcredito.proxy.ClienteProxy;
import com.microservices.msavaliadorcredito.service.AvaliadorCreditoService;
import com.microservices.msavaliadorcredito.util.DateUtil;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoServiceImpl implements AvaliadorCreditoService {

    private final ClienteProxy clienteProxy;
    private final CartoesProxy cartoesProxy;

    @Override
    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosCliente = clienteProxy.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesCliente = cartoesProxy.getCartoesPorCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosCliente.getBody())
                    .cartoes(cartoesCliente.getBody())
                    .build();
        } catch (FeignException.FeignClientException ex) {
            int status = ex.status();
            if (HttpStatus.NOT_FOUND.equals(status)) {
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicacaoMicroservicesException(ex.getMessage(), status);
        }
    }

    @Override
    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteProxy.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesProxy.getCartoesRendaAte(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();

            var cartoesAprovados = cartoes.stream()
                    .map(c -> {

                        DadosCliente dadosCliente = dadosClienteResponse.getBody();

                        BigDecimal limiteBasico = c.getLimiteBasico();
                        BigDecimal idadeBD = BigDecimal.valueOf(DateUtil.getIdade(dadosCliente.getDataAniversario()));
                        BigDecimal limiteAprovado = idadeBD.divide(BigDecimal.valueOf(10)).multiply(limiteBasico);

                        CartaoAprovado cartaoAprovado = new CartaoAprovado();
                        cartaoAprovado.setCartao(c.getNome());
                        cartaoAprovado.setBandeira(c.getBandeira());
                        cartaoAprovado.setLimiteAprovado(limiteAprovado);

                        return cartaoAprovado;
                    }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(cartoesAprovados);

        } catch (FeignException.FeignClientException ex) {
            int status = ex.status();
            if (HttpStatus.NOT_FOUND.equals(status)) {
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicacaoMicroservicesException(ex.getMessage(), status);
        }
    }

}
