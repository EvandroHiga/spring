package br.com.fiap.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDto {
    private Long id;
    private String nome;
    private String rm;
    private String cod;
}
