package br.com.fiap.spring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column
    private String password;
}
