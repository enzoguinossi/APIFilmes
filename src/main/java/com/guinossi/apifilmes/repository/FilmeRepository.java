package com.guinossi.apifilmes.repository;

import com.guinossi.apifilmes.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByTituloContainingIgnoreCase(String titulo);
    List<Filme> findByGeneroContainingIgnoreCase(String genero);
    List<Filme> findByAnoLancamentoBetween(int inicio, int fim);
    List<Filme> findByDiretorContainingIgnoreCase(String diretor);
    List<Filme> findByDuracaoMinutosIsLessThanEqualOrderByAnoLancamentoDesc(Integer duracao);
}
