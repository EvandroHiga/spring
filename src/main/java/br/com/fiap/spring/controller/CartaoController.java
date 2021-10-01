package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.Cartao;
import br.com.fiap.spring.model.dto.CartaoDto;
import br.com.fiap.spring.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cartoes")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @GetMapping(value = "{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity getCartaoByNumero(@PathVariable String numero){
        Cartao cartao = service.getCartaoByNumero(numero);

        if(cartao == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(service.cartaoModelToDto(cartao));
        }

    }






    // TODO Implementar
    public ResponseEntity getCartoesByAlunoId(@PathVariable Long idAluno){
        return null;
    }

    // TODO Implementar -> Obrigatorio estar atrelado a um aluno
    public ResponseEntity insertCartao(@RequestBody CartaoDto cartaoDto){
        return null;
    }

    // TODO Implementar
    public ResponseEntity deleteCartaoById(@PathVariable Long id){
        return null;
    }

}
