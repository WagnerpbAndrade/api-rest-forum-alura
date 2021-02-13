package br.com.wagnerandrade.alura.forum.services.interfaces;

import br.com.wagnerandrade.alura.forum.transport.TopicoDTO;
import br.com.wagnerandrade.alura.forum.transport.TopicoDetalhesDTO;
import br.com.wagnerandrade.alura.forum.transport.requests.TopicoPostRequestDTO;
import br.com.wagnerandrade.alura.forum.transport.requests.TopicoPutRequestDTO;

import java.util.List;

public interface TopicoService {

    TopicoDTO findByIdOrBadRequestException(Long id);

    TopicoDTO findById(Long id);

    List<TopicoDTO> findAll();

    List<TopicoDTO> findByNameCurso(String nomeCurso);

    TopicoDTO salvar(TopicoPostRequestDTO topicoPostRequestDTO);

    void update(TopicoPutRequestDTO topicoPutRequestDTO);

    void delete(long id);
}
