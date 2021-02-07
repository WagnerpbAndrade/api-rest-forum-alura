package br.com.wagnerandrade.alura.forum.model;

import br.com.wagnerandrade.alura.forum.enums.StatusTopicoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topico {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private StatusTopicoEnum status = StatusTopicoEnum.NAO_RESPONDIDO;
    private Usuario autor;
    private Curso curso;
    private List<Resposta> respostas = new ArrayList<>();
}
