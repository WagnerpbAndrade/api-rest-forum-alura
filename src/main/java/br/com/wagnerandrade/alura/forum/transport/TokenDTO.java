package br.com.wagnerandrade.alura.forum.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDTO {
    private String token;
    private String tipo;
}
