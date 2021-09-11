package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.dto.AuthDto;
import br.com.fiap.spring.model.dto.JwtTokenDto;
import br.com.fiap.spring.model.dto.UsuarioDto;
import br.com.fiap.spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity create (@RequestBody UsuarioDto usuarioDto){
        UsuarioDto usuarioCriado = service.create(usuarioDto);

        // TODO Colocar o path do usuario criado no header???

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public JwtTokenDto login(@RequestBody AuthDto authDto){
        return service.login(authDto);
    }

}
