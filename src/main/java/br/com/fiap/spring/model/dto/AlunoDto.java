package br.com.fiap.spring.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlunoDto {
    private Long id;
    private String nome;
    private String rm;
    private String cod;
}
