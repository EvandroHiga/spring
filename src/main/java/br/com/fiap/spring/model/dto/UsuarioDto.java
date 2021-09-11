package br.com.fiap.spring.model.dto;

import br.com.fiap.spring.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
