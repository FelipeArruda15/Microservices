package com.microservices.msavaliadorcredito.service;

import com.microservices.msavaliadorcredito.domain.model.SituacaoCliente;

public interface AvaliadorCreditoService {

    SituacaoCliente obterSituacaoCliente(String cpf);
}
