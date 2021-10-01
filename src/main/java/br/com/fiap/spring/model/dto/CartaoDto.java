package br.com.fiap.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoDto {
    private Long id;
    private Long idAluno;
    private String numero;
    private String senha;
    private String codSeg;
}
