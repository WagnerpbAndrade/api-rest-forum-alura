package br.com.wagnerandrade.alura.forum.repositories;

import br.com.wagnerandrade.alura.forum.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNome(String nome);
}
