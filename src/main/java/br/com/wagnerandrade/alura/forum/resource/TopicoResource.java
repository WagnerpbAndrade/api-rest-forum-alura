package br.com.wagnerandrade.alura.forum.resource;

import br.com.wagnerandrade.alura.forum.services.interfaces.TopicoService;
import br.com.wagnerandrade.alura.forum.transport.TopicoDTO;
import br.com.wagnerandrade.alura.forum.transport.requests.TopicoPostRequestDTO;
import br.com.wagnerandrade.alura.forum.transport.requests.TopicoPutRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/topicos")
@RequiredArgsConstructor
public class TopicoResource {
    private final TopicoService service;

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.service.findByIdOrBadRequestException(id));
    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> lista() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping("/curso")
    public ResponseEntity<List<TopicoDTO>> listaPorNomeCurso(@RequestParam String nomeCurso) {
        return ResponseEntity.ok().body(this.service.findByNameCurso(nomeCurso));
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> post(@RequestBody @Valid TopicoPostRequestDTO topicoPostRequestDTO, UriComponentsBuilder uriBuilder) {
        TopicoDTO topicoDTO = this.service.salvar(topicoPostRequestDTO);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(topicoDTO);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid TopicoPutRequestDTO topicoPutRequestDTO) {
        this.service.update(topicoPutRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
