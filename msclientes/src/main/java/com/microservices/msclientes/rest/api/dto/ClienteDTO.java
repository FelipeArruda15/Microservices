package com.microservices.msclientes.rest.api.dto;

import com.microservices.msclientes.domain.entity.Cliente;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteDTO {

    private String cpf;
    private String nome;
    private LocalDate dataAniversario;

    public Cliente toEntity(){
        return new Cliente(cpf, nome, dataAniversario);
    }
}
