package br.com.wagnerandrade.alura.forum.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicoDTO {
    protected Long id;
    protected String titulo;
    protected String mensagem;
    protected LocalDateTime dataCriacao;
}
