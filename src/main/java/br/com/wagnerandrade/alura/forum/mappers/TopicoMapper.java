package br.com.wagnerandrade.alura.forum.mappers;

import br.com.wagnerandrade.alura.forum.model.Topico;
import br.com.wagnerandrade.alura.forum.transport.TopicoDTO;
import br.com.wagnerandrade.alura.forum.transport.TopicoDetalhesDTO;
import br.com.wagnerandrade.alura.forum.transport.requests.TopicoPostRequestDTO;
import br.com.wagnerandrade.alura.forum.transport.requests.TopicoPutRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicoMapper {
    TopicoMapper instance = Mappers.getMapper(TopicoMapper.class);

    TopicoDTO toTopicoDTO(Topico topico);

    TopicoDetalhesDTO toTopicoDetalhesDTO(Topico topico);

    Topico toTopico(TopicoDTO topicoDTO);

    Topico toTopico(TopicoPostRequestDTO topicoPostRequestDTO);

    Topico toTopico(TopicoPutRequestDTO topicoPutRequestDTO);
}
