package br.com.fiap.spring.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompraDto {
    private String numCartao;
    private String senha;
    private String codSeg;
    private String estabelecimento;
    private Float valor;
    private String email;
}
