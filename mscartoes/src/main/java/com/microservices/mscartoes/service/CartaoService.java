package com.microservices.mscartoes.service;

import com.microservices.mscartoes.domain.entity.Cartao;

import java.util.List;

public interface CartaoService {

    Cartao save(Cartao cartao);

    List<Cartao> getCartoesRendaMenorIgual(Long renda);

}
