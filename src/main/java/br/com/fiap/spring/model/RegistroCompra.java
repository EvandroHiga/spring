package br.com.fiap.spring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "registro_compras")
public class RegistroCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao idCartao;
    @Column
    private String estabelecimento;
    @Column
    private Float valor;
}
