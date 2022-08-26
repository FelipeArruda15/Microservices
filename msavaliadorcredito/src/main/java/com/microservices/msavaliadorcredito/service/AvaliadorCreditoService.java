package com.microservices.msavaliadorcredito.service;

import com.microservices.msavaliadorcredito.domain.model.SituacaoCliente;
import com.microservices.msavaliadorcredito.exception.DadosClienteNotFoundException;
import com.microservices.msavaliadorcredito.exception.ErroComunicacaoMicroservicesException;

public interface AvaliadorCreditoService {

    SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException;
}
