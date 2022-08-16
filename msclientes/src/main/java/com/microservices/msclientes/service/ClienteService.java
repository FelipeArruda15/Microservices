package com.microservices.msclientes.service;

import com.microservices.msclientes.domain.entity.Cliente;
import org.aspectj.apache.bcel.generic.InstructionConstants;

import java.util.Optional;

public interface ClienteService {

    Cliente save(Cliente cliente);

    Optional<Cliente> getByCpf(String cpf);
}
