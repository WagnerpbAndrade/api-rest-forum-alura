package br.com.wagnerandrade.alura.forum.mappers;

import br.com.wagnerandrade.alura.forum.model.Topico;
import br.com.wagnerandrade.alura.forum.transport.TopicoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicoMapper {
    TopicoMapper instance = Mappers.getMapper(TopicoMapper.class);

    TopicoDTO toTopicoDTO(Topico topico);

    Topico toTopico(TopicoDTO topicoDTO);
}
