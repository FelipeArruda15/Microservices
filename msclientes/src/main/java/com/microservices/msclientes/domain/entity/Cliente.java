package com.microservices.msclientes.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAniversario;

    public Cliente(String cpf, String nome, LocalDate dataAniversario) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataAniversario = dataAniversario;
    }
}
