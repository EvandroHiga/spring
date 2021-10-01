package br.com.fiap.spring.service;

import br.com.fiap.spring.model.Cartao;
import br.com.fiap.spring.model.dto.CartaoDto;
import br.com.fiap.spring.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    public Cartao getCartaoByNumero(String numCartao){
        Optional<Cartao> cartao = repository.getCartaoByNumero(numCartao);
        if(cartao.isPresent()){
            return cartao.get();
        } else {
            return null;
        }
    }

    public CartaoDto cartaoModelToDto(Cartao cartao){
        CartaoDto dto = new CartaoDto();
        dto.setIdAluno(cartao.getAluno().getId());
        dto.setNumero(cartao.getNumero());
        dto.setSenha(cartao.getSenha());
        dto.setCodSeg(cartao.getCod_seg());
        return dto;
    }

}
