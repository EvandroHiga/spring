package br.com.fiap.spring.repository;

import br.com.fiap.spring.model.RegistroCompra;
import br.com.fiap.spring.model.dto.ConsultaCompraDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegCompraRepository extends JpaRepository<RegistroCompra, Long> {
    @Query(nativeQuery = true)
    List<ConsultaCompraDto> findComprasByClienteId(@Param("id") Long id);
}
