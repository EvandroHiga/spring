package br.com.fiap.spring.service;

import br.com.fiap.spring.model.Cartao;
import br.com.fiap.spring.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public Cartao validarCartao(String numCartao){
        Optional<Cartao> cartao = cartaoRepository.getCartaoByNumero(numCartao);

        if(cartao.isPresent()){
            return cartao.get();
        } else {
            return null;
        }
    }
}
