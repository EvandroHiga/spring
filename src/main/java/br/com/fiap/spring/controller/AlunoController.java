package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.dto.AlunoDto;
import br.com.fiap.spring.model.dto.AlunoUpdateDto;
import br.com.fiap.spring.service.AlunoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("alunos")
public class AlunoController {
    Logger logger = LoggerFactory.getLogger(AlunoController.class);

    @Autowired
    private AlunoService service;

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity getAllAlunos() {
        List<AlunoDto> listAlunos = service.getAllAlunos();
        return ResponseEntity.status(HttpStatus.OK).body(listAlunos);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAlunoById(@PathVariable Long id){
        AlunoDto aluno = service.getAlunoById(id);
        if(aluno == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(aluno);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAlunosFiltered(@RequestParam("nome") String nome){
        List<AlunoDto> alunoDtoList = service.getAlunosFiltered(nome);
        if(alunoDtoList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(alunoDtoList);
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity insertAluno(@RequestBody AlunoDto aluno){
        AlunoDto novoAluno = service.insertAluno(aluno);
        HttpHeaders httpHeaders = new HttpHeaders();

        try{
            httpHeaders.setLocation(new URI("/alunos/" + novoAluno.getId()));
        } catch(URISyntaxException e){
            logger.info(e.getMessage());
            httpHeaders.setLocation(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }

    @PutMapping("{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity updateAlunoById(@PathVariable Long id,
                                          @RequestBody AlunoUpdateDto alunoUpdateDto){
        AlunoDto aluno = service.updateAlunoById(id, alunoUpdateDto);
        if(aluno == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @DeleteMapping("{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity deleteAlunoById(@PathVariable Long id){
        try{
            service.deleteAlunoById(id);
        } catch(EmptyResultDataAccessException e){
            logger.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
