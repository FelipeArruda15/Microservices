package com.microservices.msavaliadorcredito.service.impl;

import com.microservices.msavaliadorcredito.domain.model.CartaoCliente;
import com.microservices.msavaliadorcredito.domain.model.DadosCliente;
import com.microservices.msavaliadorcredito.domain.model.SituacaoCliente;
import com.microservices.msavaliadorcredito.proxy.CartoesProxy;
import com.microservices.msavaliadorcredito.proxy.ClienteProxy;
import com.microservices.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoServiceImpl implements AvaliadorCreditoService {

    private final ClienteProxy clienteProxy;
    private final CartoesProxy cartoesProxy;

    @Override
    public SituacaoCliente obterSituacaoCliente(String cpf) {
        ResponseEntity<DadosCliente> dadosCliente = clienteProxy.dadosCliente(cpf);
        ResponseEntity<List<CartaoCliente>> cartoesCliente = cartoesProxy.getCartoesPorCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosCliente.getBody())
                .cartoes(cartoesCliente.getBody())
                .build();
    }
}
