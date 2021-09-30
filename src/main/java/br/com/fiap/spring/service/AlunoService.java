package br.com.fiap.spring.service;

import br.com.fiap.spring.model.Aluno;
import br.com.fiap.spring.model.dto.AlunoDto;
import br.com.fiap.spring.model.dto.AlunoUpdateDto;
import br.com.fiap.spring.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository repository;

    public List<AlunoDto> getAllAlunos(){
        List listAlunos = new ArrayList<AlunoDto>();
        repository.findAll().forEach(a -> listAlunos.add(alunoModelToDto(a)));
        return listAlunos;
    }

    public AlunoDto getAlunoById(Long id){
        Optional<Aluno> alunoOptional = repository.findById(id);
        if(alunoOptional.isPresent()){
            return alunoModelToDto(alunoOptional.get());
        } else {
            return null;
        }
    }

    public List<AlunoDto> getAlunosFiltered(String nome){
        List<Aluno> alunoList = repository.getAlunosFiltered(nome);
        List alunoDtoList = new ArrayList<AlunoDto>();
        if(alunoList.isEmpty()){
            return null;
        } else {
            alunoList.forEach(a -> alunoDtoList.add(alunoModelToDto(a)));
            return alunoDtoList;
        }
    }

    public AlunoDto insertAluno(AlunoDto alunoDto){
        Aluno aluno = repository.save(alunoDtoToModel(alunoDto));
        return alunoModelToDto(aluno);
    }

    public AlunoDto updateAlunoById(Long id, AlunoUpdateDto alunoUpdateDto){
        Optional<Aluno> aluno = repository.findById(id);
        if(aluno.isPresent()){
            aluno.get().setNome(alunoUpdateDto.getNome());
            aluno.get().setRm(alunoUpdateDto.getRm());
            aluno.get().setCod(alunoUpdateDto.getCod());
            return alunoModelToDto(repository.save(aluno.get()));
        } else {
            return null;
        }
    }

    public void deleteAlunoById(Long id){
        repository.deleteById(id);
    }

    private AlunoDto alunoModelToDto(Aluno aluno){
        AlunoDto dto = new AlunoDto();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setRm(aluno.getRm());
        dto.setCod(aluno.getCod());
        return dto;
    }

    private Aluno alunoDtoToModel(AlunoDto alunoDto){
        Aluno aluno = new Aluno();
        aluno.setId(alunoDto.getId());
        aluno.setNome(alunoDto.getNome().toUpperCase());
        aluno.setRm(alunoDto.getRm());
        aluno.setCod(alunoDto.getCod());
        return aluno;
    }

}
