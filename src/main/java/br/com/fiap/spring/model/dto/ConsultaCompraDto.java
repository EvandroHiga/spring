package br.com.fiap.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsultaCompraDto {
    private String nome;
    private String rm;
    private String cartao;
    private String estabelecimento;
    private Float valor;
}
