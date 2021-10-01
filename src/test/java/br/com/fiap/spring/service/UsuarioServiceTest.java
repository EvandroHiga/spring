package br.com.fiap.spring.service;

import br.com.fiap.spring.enums.Role;
import br.com.fiap.spring.model.Usuario;
import br.com.fiap.spring.model.dto.JwtTokenDto;
import br.com.fiap.spring.model.dto.UsuarioDto;
import br.com.fiap.spring.repository.UsuarioRepository;
import br.com.fiap.spring.security.JwtTokenUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    private UsuarioRepository repository;


    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void whenCreate() {

        Usuario usuario = usuarioDtoToModel(this.getUsuario());
        Usuario usuarioSalvo = repository.save(usuario);

        usuarioService.create(this.getUsuario());

        Assert.assertNotNull(usuarioSalvo);
        verify(usuarioService, times(1)).create(this.getUsuario());

    }

    @Test
    public void whenGetUsuarioById() {
        Usuario usuario = usuarioDtoToModel(this.getUsuario());
        Optional<Usuario> usuResult = repository.findById(usuario.getId());
        Assert.assertNotNull(usuResult);
    }

    @Test
    public void whenLoginOk() {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                getUsuario().getUsername(), getUsuario().getPassword());

        authenticationManager.authenticate(authToken);

        JwtTokenDto jwtTokenDto = new JwtTokenDto();

        String authenticatedToken = jwtTokenUtil.generateToken("root");

        jwtTokenDto.setJwtToken(authenticatedToken);

        Assert.assertNotNull(authenticatedToken);


    }

    @Test
    public void whenLoginNOk() {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken("", "root");

        authenticationManager.authenticate(authToken);

        JwtTokenDto jwtTokenDto = new JwtTokenDto();

        String authenticatedToken = jwtTokenUtil.generateToken("root");

        jwtTokenDto.setJwtToken(authenticatedToken);

        Assert.assertNull(authenticatedToken);

    }

    private UsuarioDto getUsuario() {

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(1L);
        usuarioDto.setUsername("root");
        usuarioDto.setPassword(passwordEncoder.encode("root"));
        usuarioDto.setRole(Role.USER);
        return usuarioDto;

    }


    private Usuario usuarioDtoToModel(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setRole(usuarioDto.getRole());
        return usuario;
    }
}
