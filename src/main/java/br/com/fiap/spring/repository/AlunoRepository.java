package br.com.fiap.spring.repository;

import br.com.fiap.spring.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("FROM Aluno a WHERE a.nome LIKE %:nome%")
    List<Aluno> getAlunosFiltered(@Param("nome") String nome);
}
