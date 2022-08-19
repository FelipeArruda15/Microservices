package com.microservices.mscartoes.rest.api.dto;

import com.microservices.mscartoes.domain.entity.Cartao;
import com.microservices.mscartoes.domain.entity.CartaoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoClienteDTO {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartaoClienteDTO fromEntity(CartaoCliente entity){
        return new CartaoClienteDTO(entity.getCartao().getNome(),
                entity.getCartao().getBandeira().toString(),
                entity.getLimite());
    }
}
