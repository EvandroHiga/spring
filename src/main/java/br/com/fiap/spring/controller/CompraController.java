package br.com.fiap.spring.controller;

import br.com.fiap.spring.model.dto.CompraDto;
import br.com.fiap.spring.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("compra")
public class CompraController {

    @Autowired
    private CompraService service;

    @PostMapping
    public void autorizarCompra(@RequestBody CompraDto compraDto){
        service.autorizarCompra(compraDto);
    }
}
