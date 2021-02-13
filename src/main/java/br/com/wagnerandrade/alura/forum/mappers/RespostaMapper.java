package br.com.wagnerandrade.alura.forum.mappers;

import br.com.wagnerandrade.alura.forum.model.Resposta;
import br.com.wagnerandrade.alura.forum.transport.RespostaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RespostaMapper {
    RespostaMapper instance = Mappers.getMapper(RespostaMapper.class);

    RespostaDTO toRespostaDTO(Resposta resposta);
}
