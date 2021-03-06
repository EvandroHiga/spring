package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.Cartao;
import br.com.fiap.spring.model.dto.CartaoDto;
import br.com.fiap.spring.service.CartaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static br.com.fiap.spring.utils.MessageConstants.ERRO_CLIENTE_INEXISTENTE;
import static br.com.fiap.spring.utils.MessageConstants.ERRO_CRIAR_CARTAO;

@RestController
@RequestMapping("cartoes")
public class CartaoController {
    Logger logger = LoggerFactory.getLogger(CartaoController.class);

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

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity insertCartao(@RequestBody CartaoDto cartaoDto) {
        CartaoDto cartaoCriado;
        HttpHeaders httpHeaders = new HttpHeaders();

        try{
            cartaoCriado = service.insertCartao(cartaoDto);
            if(cartaoCriado == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERRO_CLIENTE_INEXISTENTE);
            } else {
                httpHeaders.setLocation(new URI("/cartoes/" + cartaoCriado.getNumero()));
            }
        } catch(DataIntegrityViolationException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERRO_CRIAR_CARTAO);
        } catch(URISyntaxException exception){
            logger.info(exception.getMessage());
            httpHeaders.setLocation(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();

    }

    @DeleteMapping("{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity deleteCartaoById(@PathVariable Long id){
        try{
            service.deleteCartaoById(id);
        } catch(EmptyResultDataAccessException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
