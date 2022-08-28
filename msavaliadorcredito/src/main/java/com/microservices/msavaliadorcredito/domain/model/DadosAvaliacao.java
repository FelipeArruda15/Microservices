package com.microservices.msavaliadorcredito.domain.model;

import lombok.Data;
import org.bouncycastle.cms.PasswordRecipientId;

import java.math.BigDecimal;

@Data
public class DadosAvaliacao {

    private String cpf;
    private Long renda;
}
