package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.dto.CartaoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cartoes")
public class CartaoController {

    // TODO Implementar
    public ResponseEntity getCartaoById(@PathVariable Long id){
        return null;
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
