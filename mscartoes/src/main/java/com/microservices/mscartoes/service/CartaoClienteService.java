package com.microservices.mscartoes.service;

import com.microservices.mscartoes.domain.entity.CartaoCliente;

import java.util.List;

public interface CartaoClienteService {

    List<CartaoCliente> listCartoesByCpf(String cpf);
}
