package com.guinossi.apifilmes.service;

import com.guinossi.apifilmes.dto.FilmeRequestDTO;
import com.guinossi.apifilmes.dto.FilmeResponseDTO;
import com.guinossi.apifilmes.entity.Filme;
import com.guinossi.apifilmes.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {
    private final FilmeRepository filmeRepository;

    FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public FilmeResponseDTO criar(FilmeRequestDTO filmeRequestDTO) {
        return FilmeResponseDTO.fromEntity(filmeRepository.save(FilmeRequestDTO.toEntity(filmeRequestDTO)));
    }

    public List<FilmeResponseDTO> listar() {
        return FilmeResponseDTO.fromEntity(filmeRepository.findAll());
    }

    public List<FilmeResponseDTO> buscarPorTitulo(String titulo) {
        return FilmeResponseDTO.fromEntity(filmeRepository.findByTituloContainingIgnoreCase(titulo));
    }

    public List<FilmeResponseDTO> buscarPorGenero(String genero) {
        return FilmeResponseDTO.fromEntity(filmeRepository.findByGeneroContainingIgnoreCase(genero));
    }

    public List<FilmeResponseDTO> buscarPorAno(int inicio, int fim) {
        return FilmeResponseDTO.fromEntity(filmeRepository.findByAnoLancamentoBetween(inicio, fim));
    }

    public List<FilmeResponseDTO> buscarPorDiretor(String diretor) {
        return FilmeResponseDTO.fromEntity(filmeRepository.findByDiretorContainingIgnoreCase(diretor));
    }

    public List<FilmeResponseDTO> buscarPorDuracao(Integer duracao) {
        return FilmeResponseDTO.fromEntity(
                filmeRepository.findByDuracaoMinutosIsLessThanEqualOrderByAnoLancamentoDesc(duracao));
    }

    public FilmeResponseDTO alterar(Long id, FilmeRequestDTO filmeRequestDTO) {
        Filme filme = filmeRepository.findById(id).orElseThrow();
        filme.setTitulo(filmeRequestDTO.getTitulo());
        filme.setAnoLancamento(filmeRequestDTO.getAnoLancamento());
        filme.setDiretor(filmeRequestDTO.getDiretor());
        filme.setGenero(filmeRequestDTO.getGenero());
        filme.setIdioma(filmeRequestDTO.getIdioma());
        filme.setDuracaoMinutos(filmeRequestDTO.getDuracaoMinutos());
        filme.setClassificacaoIndicativa(filmeRequestDTO.getClassificacaoIndicativa());
        filme.setNota(filmeRequestDTO.getNota());
        return FilmeResponseDTO.fromEntity(filmeRepository.save(filme));
    }

    public void deletar(Long id) {
        filmeRepository.deleteById(id);
    }
}
