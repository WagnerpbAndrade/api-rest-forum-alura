package br.com.wagnerandrade.alura.forum.resource;

import br.com.wagnerandrade.alura.forum.configurer.security.AuthTokenService;
import br.com.wagnerandrade.alura.forum.transport.TokenDTO;
import br.com.wagnerandrade.alura.forum.transport.requests.LoginPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AutenticacaoResource {

    private final AuthenticationManager authenticationManager;
    private final AuthTokenService authTokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginPostRequestDTO loginPostRequestDTO) {
        UsernamePasswordAuthenticationToken dadosLogin = loginPostRequestDTO.converter();

        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = authTokenService.gerarToken(authenticate);
            return ResponseEntity.ok().body(new TokenDTO(token, "Bearer"));
        }catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
