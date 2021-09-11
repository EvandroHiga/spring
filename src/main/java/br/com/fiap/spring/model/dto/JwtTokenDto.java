package br.com.fiap.spring.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtTokenDto {
    private String jwtToken;
}
