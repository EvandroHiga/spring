package br.com.fiap.spring.service;


import br.com.fiap.spring.model.Cartao;
import br.com.fiap.spring.repository.CartaoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CartaoServiceTest {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    CartaoService cartaoService;

    @Test
    public void devePegarCartaoPorNumero(){
        //cenario
        Cartao cartao1 = Cartao.builder().numero("123").build();
        cartaoRepository.save(cartao1);
        // teste
        Optional<Cartao> result = cartaoRepository.getCartaoByNumero("123");
        boolean resultado = cartao1.getNumero().equals(result.get().getNumero());
        //Verificar
        Assertions.assertThat(resultado).isTrue();
    }


    @Test
    public void deveValidarCartao(){
        //cenario
        Optional<Cartao> cartao1 = cartaoRepository.getCartaoByNumero("123");
        //teste
        Cartao result = cartaoService.validarCartao(cartao1.get().getNumero());
        //verificar
        Assertions.assertThat(result).isNotNull();
    }

}
