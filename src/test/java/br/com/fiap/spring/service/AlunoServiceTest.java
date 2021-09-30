package br.com.fiap.spring.service;

import br.com.fiap.spring.model.Aluno;
import br.com.fiap.spring.model.dto.AlunoDto;
import br.com.fiap.spring.repository.AlunoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class AlunoServiceTest {


    @Mock
    private AlunoRepository repository;

    @Autowired
    AlunoService alunoService;

    @Mock
    private Aluno aluno;


    @Test
    public void whenGetAlunoById() {
        Aluno aluno = Aluno.builder().id(1L).build();
        Optional<Aluno> alunoResult = repository.findById(1L);
        Assert.assertNotNull(alunoResult);

    }

    @Test
    public void whenGetAllAlunos() {
        List listAlunos = new ArrayList<AlunoDto>();
        repository.findAll().forEach(a -> listAlunos.add(this.alunoMock()));
        Assert.assertNotNull(listAlunos);
    }

    @Test
    public void whenInsertAluno() {
        Aluno alunoNew = alunoDtoToModel(this.alunoMock());
        repository.save(alunoNew);
        Assert.assertNotNull(alunoNew);
        verify(repository, times(1)).save(alunoNew);
    }

    @Test
    public void whenDeleteAluno() {
        long id = 2L;
        repository.deleteById(id);
        verify(repository, times(1)).deleteById(id);
    }


    private Aluno alunoDtoToModel(AlunoDto alunoDto) {
        Aluno aluno = new Aluno();
        aluno.setId(alunoDto.getId());
        aluno.setNome(alunoDto.getNome());
        aluno.setRm(alunoDto.getRm());
        aluno.setCod(alunoDto.getCod());
        return aluno;
    }

    private AlunoDto alunoMock() {
        AlunoDto aluno = new AlunoDto();
        aluno.setId(1L);
        aluno.setNome("Paula Soler");
        aluno.setRm("54877");
        aluno.setCod("1234");
        return aluno;
    }
}

