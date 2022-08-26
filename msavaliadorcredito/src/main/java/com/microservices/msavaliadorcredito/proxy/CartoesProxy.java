package com.microservices.msavaliadorcredito.proxy;

import com.microservices.msavaliadorcredito.domain.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesProxy {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesPorCliente(@RequestParam("cpf") String cpf);

}