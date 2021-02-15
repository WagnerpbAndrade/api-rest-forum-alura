package br.com.wagnerandrade.alura.forum.configurer.security;

import br.com.wagnerandrade.alura.forum.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthTokenService {

    @Value("${spring.forum.jwt.secret}")
    private String secret;

    @Value("${spring.forum.jwt.expiration}")
    private String expiration;

    public String gerarToken(Authentication authenticate) {
        Usuario logado = (Usuario) authenticate.getPrincipal();
        Date now = new Date();
        Date dataExpiracao = new Date(now.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API do Fórum da Alura")
                .setSubject(logado.getId().toString())
                .setIssuedAt(now)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
