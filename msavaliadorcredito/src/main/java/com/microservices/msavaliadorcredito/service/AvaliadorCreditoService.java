package com.microservices.msavaliadorcredito.service;

import com.microservices.msavaliadorcredito.domain.entity.SituacaoCliente;

public interface AvaliadorCreditoService {

    SituacaoCliente obterSituacaoCliente(String cpf);
}
