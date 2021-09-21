package br.com.fiap.spring.repository;

import br.com.fiap.spring.model.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void deveUsuarioAcharPorUsername(){
        //cenario
        Usuario usuario = Usuario.builder().username("usuario").password("123").build();
        usuarioRepository.save(usuario);
        //acao
        Usuario result = usuarioRepository.findByUsername("usuario");
        //verificacao
        Assertions.assertThat(result).isNotNull();
    }
}
