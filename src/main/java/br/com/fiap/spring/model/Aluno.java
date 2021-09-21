package br.com.fiap.spring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;

    @Column
    private String nome;

    @Column
    private String rm;

    @Column
    private String cod;
}
