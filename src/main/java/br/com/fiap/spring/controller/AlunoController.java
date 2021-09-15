package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.dto.AlunoDto;
import br.com.fiap.spring.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    @Autowired
    AlunoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN") // TODO Annotation ou HttpSecurity?
    public ResponseEntity getAllAlunos(){
        List<AlunoDto> listAlunos = service.getAllAlunos();
        return ResponseEntity.status(HttpStatus.OK).body(listAlunos);
    }

    @GetMapping("{id}")
    public ResponseEntity getAlunoById(@PathVariable Long id){
        AlunoDto aluno = service.getAlunoById(id);

        if(aluno == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(aluno);
        }
    }

    @PostMapping
    public ResponseEntity insertAluno(@RequestBody AlunoDto aluno){
        AlunoDto novoAluno = service.insertAluno(aluno);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Id-Novo-Aluno", String.valueOf(novoAluno.getId().intValue()));

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }

}
