package com.microservices.mscartoes.service.impl;

import com.microservices.mscartoes.domain.entity.CartaoCliente;
import com.microservices.mscartoes.repository.CartaoClienteRepository;
import com.microservices.mscartoes.service.CartaoClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoClienteServiceImpl implements CartaoClienteService {

    private final CartaoClienteRepository repository;

    public List<CartaoCliente> listCartoesByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
