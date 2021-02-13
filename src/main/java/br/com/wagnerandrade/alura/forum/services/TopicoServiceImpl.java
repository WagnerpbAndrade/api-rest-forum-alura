package br.com.wagnerandrade.alura.forum.services;

import br.com.wagnerandrade.alura.forum.infra.exceptions.BadRequestException;
import br.com.wagnerandrade.alura.forum.mappers.TopicoMapper;
import br.com.wagnerandrade.alura.forum.model.Curso;
import br.com.wagnerandrade.alura.forum.model.Topico;
import br.com.wagnerandrade.alura.forum.repositories.CursoRepository;
import br.com.wagnerandrade.alura.forum.repositories.TopicoRepository;
import br.com.wagnerandrade.alura.forum.services.interfaces.TopicoService;
import br.com.wagnerandrade.alura.forum.transport.TopicoDTO;
import br.com.wagnerandrade.alura.forum.transport.TopicoDetalhesDTO;
import br.com.wagnerandrade.alura.forum.transport.requests.TopicoPostRequestDTO;
import br.com.wagnerandrade.alura.forum.transport.requests.TopicoPutRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicoServiceImpl implements TopicoService {
    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final TopicoMapper mapper;

    @Override
    public TopicoDetalhesDTO findByIdOrBadRequestException(Long id) {
        Topico topico = this.topicoRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("Tópico não encontrato"));
        return new TopicoDetalhesDTO(topico);
    }

    @Override
    public TopicoDTO findById(Long id) {
        return this.mapper.toTopicoDTO(
                this.topicoRepository
                        .findById(id)
                        .orElseThrow(() -> new BadRequestException("Tópico não encontrato"))
        );
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TopicoDTO> findAll() {
        return this.topicoRepository.findAll().stream().map(mapper::toTopicoDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TopicoDTO> findByNameCurso(String nomeCurso) {
        return this.topicoRepository.findByCursoNome(nomeCurso).stream().map(mapper::toTopicoDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TopicoDTO salvar(TopicoPostRequestDTO topicoPostRequestDTO) {
        Optional<Curso> optionalCurso = this.cursoRepository.findByNome(topicoPostRequestDTO.getNomeCurso());
        Curso curso = optionalCurso.orElseThrow(() -> new BadRequestException("Curso não encontrato"));
        Topico topico = Topico.builder()
                .titulo(topicoPostRequestDTO.getTitulo())
                .mensagem(topicoPostRequestDTO.getMensagem())
                .curso(curso)
                .dataCriacao(LocalDateTime.now())
                .build();
        return this.mapper.toTopicoDTO(this.topicoRepository.save(topico));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TopicoPutRequestDTO topicoPutRequestDTO) {
        Topico topico = this.mapper.toTopico(this.findById(topicoPutRequestDTO.getId()));

        update(topico, topicoPutRequestDTO);

        this.topicoRepository.save(topico);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(long id) {
        this.topicoRepository.deleteById(this.findById(id).getId());
    }

    private void update(Topico oldObj, TopicoPutRequestDTO newObj) {
        oldObj.setTitulo(newObj.getTitulo());
        oldObj.setMensagem(newObj.getMensagem());
    }
}
