package br.com.fiap.spring.repository;

import br.com.fiap.spring.model.RegistroCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegCompraRepository extends JpaRepository<RegistroCompra, Long> { }
