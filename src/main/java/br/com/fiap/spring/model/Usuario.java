package br.com.fiap.spring.model;

import br.com.fiap.spring.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usuarios")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
