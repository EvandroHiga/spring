package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.dto.CompraDto;
import br.com.fiap.spring.model.dto.ConsultaCompraDto;
import br.com.fiap.spring.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fiap.spring.utils.MessageConstants.COMPRA_SUCESSO;
import static br.com.fiap.spring.utils.MessageConstants.COMPRA_SUCESSO_ERRO_COMPROVANTE;

@RestController
@RequestMapping("compras")
public class CompraController {

    @Autowired
    private CompraService service;

    @PostMapping
    public ResponseEntity autorizarCompra(@RequestBody CompraDto compraDto){
        String retorno = service.autorizarCompra(compraDto);
        if(COMPRA_SUCESSO.equals(retorno) || COMPRA_SUCESSO_ERRO_COMPROVANTE.equals(retorno)){
            return ResponseEntity.status(HttpStatus.OK).body(retorno);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
        }
    }

    @GetMapping(value = "aluno/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity consultarComprasByClienteId(@PathVariable Long id){
        List<ConsultaCompraDto> consultaCompraDtoList = service.consultarComprasByClienteId(id);

        if(consultaCompraDtoList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(consultaCompraDtoList);
        }

    }

}
