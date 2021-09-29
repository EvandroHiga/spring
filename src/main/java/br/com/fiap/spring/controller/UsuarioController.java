package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.dto.AuthDto;
import br.com.fiap.spring.model.dto.JwtTokenDto;
import br.com.fiap.spring.model.dto.UsuarioDto;
import br.com.fiap.spring.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static br.com.fiap.spring.utils.MessageConstants.ERRO_USERNAME_PASSWD;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsuarioById(@PathVariable Long id){
        UsuarioDto usuarioDto = service.getUsuarioById(id);
        if(usuarioDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioDto);
        }
    }

    @PostMapping
    public ResponseEntity create (@RequestBody UsuarioDto usuarioDto){
        UsuarioDto usuarioCriado = service.create(usuarioDto);
        HttpHeaders httpHeaders = new HttpHeaders();

        try{
            httpHeaders.setLocation(new URI("/usuarios/" + usuarioCriado.getId()));
        } catch(URISyntaxException e){
            logger.info(e.getMessage());
            httpHeaders.setLocation(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthDto authDto){
        try {
            JwtTokenDto token = service.login(authDto);
            return ResponseEntity.status(HttpStatus.OK).body(token.getJwtToken());
        } catch (InternalAuthenticationServiceException | BadCredentialsException exception){
            logger.info(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERRO_USERNAME_PASSWD);
        }

    }

}
