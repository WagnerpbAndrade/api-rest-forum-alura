package br.com.wagnerandrade.alura.forum.transport;

import br.com.wagnerandrade.alura.forum.enums.StatusTopicoEnum;
import br.com.wagnerandrade.alura.forum.mappers.RespostaMapper;
import br.com.wagnerandrade.alura.forum.model.Topico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TopicoDetalhesDTO extends TopicoDTO {
    private String nomeAutor;
    private StatusTopicoEnum status;
    private List<RespostaDTO> respostas;

    public TopicoDetalhesDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.nomeAutor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas = topico.getRespostas().stream().map(RespostaMapper.instance::toRespostaDTO).collect(Collectors.toList());
    }
}
