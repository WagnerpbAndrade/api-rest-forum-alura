package br.com.wagnerandrade.alura.forum.configurer.security;

import br.com.wagnerandrade.alura.forum.model.Usuario;
import br.com.wagnerandrade.alura.forum.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private final AuthTokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);
        final var valido = tokenService.isTokenValido(token);
        if (valido) {
            autenticarCliente(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarCliente(String token) {
        final var idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = this.usuarioRepository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }
}
