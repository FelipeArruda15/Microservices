package com.microservices.msavaliadorcredito.service.impl;

import com.microservices.msavaliadorcredito.domain.model.CartaoCliente;
import com.microservices.msavaliadorcredito.domain.model.DadosCliente;
import com.microservices.msavaliadorcredito.domain.model.SituacaoCliente;
import com.microservices.msavaliadorcredito.exception.DadosClienteNotFoundException;
import com.microservices.msavaliadorcredito.exception.ErroComunicacaoMicroservicesException;
import com.microservices.msavaliadorcredito.proxy.CartoesProxy;
import com.microservices.msavaliadorcredito.proxy.ClienteProxy;
import com.microservices.msavaliadorcredito.service.AvaliadorCreditoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
