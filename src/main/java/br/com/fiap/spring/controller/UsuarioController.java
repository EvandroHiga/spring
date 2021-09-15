package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.dto.AuthDto;
import br.com.fiap.spring.model.dto.JwtTokenDto;
import br.com.fiap.spring.model.dto.UsuarioDto;
import br.com.fiap.spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("{id}")
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
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location","/usuarios/" + usuarioCriado.getId())
                .build();
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthDto authDto){
        JwtTokenDto token = service.login(authDto);
        return ResponseEntity.status(HttpStatus.OK).body(token.getJwtToken());
    }

}
