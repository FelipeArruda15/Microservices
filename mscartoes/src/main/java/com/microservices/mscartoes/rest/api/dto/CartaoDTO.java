package com.microservices.mscartoes.rest.api.dto;

import com.microservices.mscartoes.domain.entity.Cartao;
import com.microservices.mscartoes.domain.enums.BandeiraCartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoDTO {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao toEntity(){
        return new Cartao(nome, bandeira, renda, limiteBasico);
    }
}
