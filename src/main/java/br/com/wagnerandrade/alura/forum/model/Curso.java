package br.com.wagnerandrade.alura.forum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {
    private Long id;
    private String nome;
    private String categoria;
}
