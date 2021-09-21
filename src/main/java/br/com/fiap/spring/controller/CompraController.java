package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.dto.CompraDto;
import br.com.fiap.spring.model.dto.ConsultaCompraDto;
import br.com.fiap.spring.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fiap.spring.utils.MessageConstants.COMPRA_SUCESSO;

@RestController
@RequestMapping("compras")
public class CompraController {

    @Autowired
    private CompraService service;

    @PostMapping
    public ResponseEntity autorizarCompra(@RequestBody CompraDto compraDto){
        String retorno = service.autorizarCompra(compraDto);
        if(COMPRA_SUCESSO.equals(retorno)){
            return ResponseEntity.status(HttpStatus.OK).body(retorno);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
        }
    }

    @GetMapping("aluno/{id}")
    public ResponseEntity consultarComprasByClienteId(@PathVariable Long id){
        List<ConsultaCompraDto> consultaCompraDtoList = service.consultarComprasByClienteId(id);
        return ResponseEntity.status(HttpStatus.OK).body(consultaCompraDtoList);
    }

}
