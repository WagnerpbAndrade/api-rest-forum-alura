package br.com.wagnerandrade.alura.forum.resource;

import br.com.wagnerandrade.alura.forum.mappers.TopicoMapper;
import br.com.wagnerandrade.alura.forum.model.Curso;
import br.com.wagnerandrade.alura.forum.model.Topico;
import br.com.wagnerandrade.alura.forum.transport.TopicoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/topicos")
public class TopicoResource {

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> lista() {
        Curso curso = Curso.builder()
                .nome("Spring")
                .categoria("Programação")
                .build();

        Topico topico = Topico.builder()
                .titulo("Dúvida")
                .mensagem("Dúvida com Spring")
                .curso(curso)
                .dataCriacao(LocalDateTime.now())
                .build();

        TopicoDTO topicoDTO = TopicoMapper.instance.toTopicoDTO(topico);

        return ResponseEntity.ok().body(List.of(topicoDTO, topicoDTO, topicoDTO));
    }
}
