package br.com.fiap.spring.security;

import br.com.fiap.spring.model.Usuario;
import br.com.fiap.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (usuario.isPresent()){
            List roles = new ArrayList<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority(usuario.get().getRole().getDescricao()));

            return new User(
                    usuario.get().getUsername(),
                    usuario.get().getPassword(),
                    roles);
        } else {
            throw new UsernameNotFoundException(
                    new StringBuilder().append("Usuario ").append(username).append(" nao encontrado").toString());
        }
    }
}
