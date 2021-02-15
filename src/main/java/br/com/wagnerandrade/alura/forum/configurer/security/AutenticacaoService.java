package br.com.wagnerandrade.alura.forum.configurer.security;

import br.com.wagnerandrade.alura.forum.model.Usuario;
import br.com.wagnerandrade.alura.forum.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(username);
        return usuario.orElseThrow(() -> new UsernameNotFoundException("Dados inválidos"));
    }
}
