package br.com.wagnerandrade.alura.forum.transport.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class TopicoPutRequestDTO {

    private Long id;

    @NotNull(message = "O título não pode ser inválido")
    @NotEmpty(message = "O título não pode ser vazio")
    @Length(min = 5, message = "O título precisa ter no mínimo 5 carateres")
    private String titulo;

    @NotNull(message = "A mensagem não pode ser inválido")
    @NotEmpty(message = "A mensagem não pode ser vazio")
    private String mensagem;
}
