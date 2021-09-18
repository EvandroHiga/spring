package br.com.fiap.spring.repository;

import br.com.fiap.spring.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    @Query("select c from Cartao c where c.numero = :numero")
    Optional<Cartao> getCartaoByNumero(@Param("numero") String numero);
}
