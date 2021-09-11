package br.com.fiap.spring.service;

import br.com.fiap.spring.model.Usuario;
import br.com.fiap.spring.model.dto.AuthDto;
import br.com.fiap.spring.model.dto.JwtTokenDto;
import br.com.fiap.spring.model.dto.UsuarioDto;
import br.com.fiap.spring.repository.UsuarioRepository;
import br.com.fiap.spring.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.com.fiap.spring.utils.MessageConstants.ERRO_CREDENCIAL_INVALIDA;
import static br.com.fiap.spring.utils.MessageConstants.ERRO_USUARIO_DESABILITADO;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UsuarioDto create(UsuarioDto createUserDTO) {
        Usuario usuario = new Usuario();

        usuario.setUsername(createUserDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));


        //TODO Settar a role do usuario novo
        usuario.setRole(createUserDTO.getRole());


        Usuario usuarioSalvo = repository.save(usuario);

        return usuarioModelToDto(usuarioSalvo);
    }

    public JwtTokenDto login(AuthDto authDto){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
        try{
            authenticationManager.authenticate(authToken);
        } catch (DisabledException disabledException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_USUARIO_DESABILITADO);
        } catch (BadCredentialsException badCredentialsException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_CREDENCIAL_INVALIDA);
        }

        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        String authenticatedToken = jwtTokenUtil.generateToken(authDto.getUsername());

        jwtTokenDto.setJwtToken(authenticatedToken);

        return jwtTokenDto;
    }


    private UsuarioDto usuarioModelToDto (Usuario usuario){
        UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setPassword(usuario.getPassword());
        return dto;
    }
}
