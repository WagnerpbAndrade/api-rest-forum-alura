package br.com.wagnerandrade.alura.forum.repositories;

import br.com.wagnerandrade.alura.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String name);
}
