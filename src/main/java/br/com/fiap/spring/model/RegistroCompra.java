package br.com.fiap.spring.model;

import br.com.fiap.spring.model.dto.ConsultaCompraDto;
import lombok.Data;

import javax.persistence.*;

@NamedNativeQuery(
        name = "RegistroCompra.findComprasByClienteId",
        query = "SELECT a.nome, " +
                "a.rm, " +
                "c.numero AS cartao, " +
                "rc.estabelecimento, " +
                "rc.valor " +
                "FROM alunos a INNER JOIN cartoes c " +
                "ON a.id = c.id_aluno INNER JOIN registro_compras rc " +
                "ON c.id = rc.id_cartao " +
                "WHERE a.id = :id",
        resultSetMapping = "Mapping.ConsultaCompraDto")
@SqlResultSetMapping(
        name = "Mapping.ConsultaCompraDto",
        classes = @ConstructorResult(
                targetClass = ConsultaCompraDto.class,
                columns = {
                        @ColumnResult(name = "nome", type = String.class),
                        @ColumnResult(name = "rm", type = String.class),
                        @ColumnResult(name = "cartao", type = String.class),
                        @ColumnResult(name = "estabelecimento", type = String.class),
                        @ColumnResult(name = "valor", type = Float.class)
                }
        )
)

@Entity
@Data
@Table(name = "registro_compras")
public class RegistroCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;

    @Column
    private String estabelecimento;

    @Column
    private Float valor;
}
