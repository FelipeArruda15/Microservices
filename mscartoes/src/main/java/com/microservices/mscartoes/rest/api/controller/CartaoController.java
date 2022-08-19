package com.microservices.mscartoes.rest.api.controller;

import com.microservices.mscartoes.domain.entity.Cartao;
import com.microservices.mscartoes.domain.entity.CartaoCliente;
import com.microservices.mscartoes.repository.CartaoClienteRepository;
import com.microservices.mscartoes.rest.api.dto.CartaoClienteDTO;
import com.microservices.mscartoes.rest.api.dto.CartaoDTO;
import com.microservices.mscartoes.service.CartaoClienteService;
import com.microservices.mscartoes.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService service;
    private final CartaoClienteService cartaoClienteService;

    @GetMapping
    public String status(){
        return "OK";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoDTO cartaoDTO){
        Cartao cartao = cartaoDTO.toEntity();
        service.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda){
        List<Cartao> cartoes =  service.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoClienteDTO>> getCartoesPorCliente(@RequestParam("cpf") String cpf){
        List<CartaoCliente> cartaoClientes = cartaoClienteService
                .listCartoesByCpf(cpf);

        List<CartaoClienteDTO> resultList = cartaoClientes
                .stream()
                .map(c -> CartaoClienteDTO.fromEntity(c))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultList);
    }

}
