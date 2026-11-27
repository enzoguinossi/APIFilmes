package com.guinossi.apifilmes.entity;

import com.guinossi.apifilmes.dto.FilmeRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer anoLancamento;
    private String genero;
    private String diretor;
    private String idioma;
    private Integer duracaoMinutos;
    private String classificacaoIndicativa;
    private Double nota;
    @CreationTimestamp
    private LocalDateTime criadoEm;
    @UpdateTimestamp
    private LocalDateTime atualizadoEm;

    public Filme() {
    }

    public Filme(String titulo, Integer anoLancamento, String genero, String diretor, String idioma, Integer duracaoMinutos, String classificacaoIndicativa, Double nota) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.diretor = diretor;
        this.idioma = idioma;
        this.duracaoMinutos = duracaoMinutos;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.nota = nota;
    }

    public Filme(FilmeRequestDTO filmeRequestDTO) {
        this.titulo = filmeRequestDTO.getTitulo();
        this.anoLancamento = filmeRequestDTO.getAnoLancamento();
        this.genero = filmeRequestDTO.getGenero();
        this.diretor = filmeRequestDTO.getDiretor();
        this.idioma = filmeRequestDTO.getIdioma();
        this.duracaoMinutos = filmeRequestDTO.getDuracaoMinutos();
        this.classificacaoIndicativa = filmeRequestDTO.getClassificacaoIndicativa();
        this.nota = filmeRequestDTO.getNota();
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(String classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }
}
