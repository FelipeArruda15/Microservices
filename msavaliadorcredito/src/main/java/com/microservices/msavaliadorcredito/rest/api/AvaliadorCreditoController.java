package com.microservices.msavaliadorcredito.rest.api;

import com.microservices.msavaliadorcredito.domain.model.DadosAvaliacao;
import com.microservices.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import com.microservices.msavaliadorcredito.domain.model.SituacaoCliente;
import com.microservices.msavaliadorcredito.exception.DadosClienteNotFoundException;
import com.microservices.msavaliadorcredito.exception.ErroComunicacaoMicroservicesException;
import com.microservices.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService service;

    @GetMapping
    public String status(){
        return "OK";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        try {
            SituacaoCliente situacaoCliente = service.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        }catch (DadosClienteNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (ErroComunicacaoMicroservicesException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados){
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = service
                    .realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        }catch (DadosClienteNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (ErroComunicacaoMicroservicesException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

}
