package br.com.fiap.spring.security;

import br.com.fiap.spring.model.Usuario;
import br.com.fiap.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByUsername(username));
        if (usuario.isPresent()){
            return new User(usuario.get().getUsername(), usuario.get().getPassword(), new ArrayList<>()); // TODO Roles do usuario: implementar. Como???
        } else {
            throw new UsernameNotFoundException(
                    new StringBuilder().append("Usuario ").append(username).append(" nao encontrado").toString());
        }
    }
}
