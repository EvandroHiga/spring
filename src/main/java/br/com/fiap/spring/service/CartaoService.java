package br.com.fiap.spring.service;

import br.com.fiap.spring.model.Aluno;
import br.com.fiap.spring.model.Cartao;
import br.com.fiap.spring.model.dto.CartaoDto;
import br.com.fiap.spring.repository.AlunoRepository;
import br.com.fiap.spring.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public Cartao getCartaoByNumero(String numCartao){
        Optional<Cartao> cartao = cartaoRepository.getCartaoByNumero(numCartao);
        if(cartao.isPresent()){
            return cartao.get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public CartaoDto insertCartao(CartaoDto cartaoDto){
        Optional<Aluno> aluno = alunoRepository.findById(cartaoDto.getIdAluno());
        if(aluno.isPresent()){
            Cartao cartao = new Cartao();
            cartao.setAluno(aluno.get());
            cartao.setNumero(cartaoDto.getNumero());
            cartao.setSenha(cartaoDto.getSenha());
            cartao.setCod_seg(cartaoDto.getCodSeg());
            return cartaoModelToDto(cartaoRepository.save(cartao));
        } else{
            return null;
        }
    }

    public void deleteCartaoById(Long id){ cartaoRepository.deleteById(id); }

    public CartaoDto cartaoModelToDto(Cartao cartao){
        CartaoDto dto = new CartaoDto();
        dto.setId(cartao.getId());
        dto.setIdAluno(cartao.getAluno().getId());
        dto.setNumero(cartao.getNumero());
        dto.setSenha(cartao.getSenha());
        dto.setCodSeg(cartao.getCod_seg());
        return dto;
    }

}
