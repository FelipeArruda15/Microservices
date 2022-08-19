package com.microservices.mscartoes.service.impl;

import com.microservices.mscartoes.domain.entity.Cartao;
import com.microservices.mscartoes.repository.CartaoRepository;
import com.microservices.mscartoes.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoServiceImpl implements CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao){
        return repository.save(cartao);
    }

    @Transactional(readOnly = true)
    public List<Cartao> getCartoesRendaMenorIgual(@RequestParam("renda") Long renda){
        var rendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }

}
