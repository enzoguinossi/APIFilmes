package com.guinossi.apifilmes.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.guinossi.apifilmes.entity.Filme;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(value = {"id", "titulo", "anoLancamento", "genero", "diretor", "idioma", "duracaoHoras", "classificacaoIndicativa", "nota", "avaliacao", "criadoEm", "atualizadoEm"})
public class FilmeResponseDTO {
    private long id;
    private String titulo;
    private Integer anoLancamento;
    private String genero;
    private String diretor;
    private String idioma;
    private String duracaoHoras;
    private String classificacaoIndicativa;
    private Double nota;
    private Avaliacao_enum avaliacao;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public FilmeResponseDTO() {
    }

    public FilmeResponseDTO(Filme filme) {
        this.id = filme.getId();
        this.titulo = filme.getTitulo();
        this.anoLancamento = filme.getAnoLancamento();
        this.genero = filme.getGenero();
        this.diretor = filme.getDiretor();
        this.idioma = filme.getIdioma();
        this.classificacaoIndicativa = filme.getClassificacaoIndicativa();
        this.nota = filme.getNota();
        this.duracaoHoras = FilmeResponseDTO.minutosParaHoras(filme.getDuracaoMinutos());
        this.avaliacao = notaParaAvaliacao(filme.getNota());
        this.criadoEm = filme.getCriadoEm();
        this.atualizadoEm = filme.getAtualizadoEm();
    }

    private static String minutosParaHoras(int totalMinutos) {
        int horas = totalMinutos / 60;
        int minutos = totalMinutos % 60;
        if (minutos == 0) {
            return String.format("%dh", horas);
        } else if (horas == 0) {
            return String.format("%dmin", minutos);
        } else return String.format("%dh e %dmin", horas, minutos);
    }

    private static Avaliacao_enum notaParaAvaliacao(double nota) {
        if (nota > 9) {
            return Avaliacao_enum.OBRAPRIMA;
        } else if (nota < 7) {
            return Avaliacao_enum.REGULAR;
        } else return Avaliacao_enum.RECOMENDADO;
    }

    public static FilmeResponseDTO fromEntity(Filme filme) {
        return new FilmeResponseDTO(filme);
    }

    public static List<FilmeResponseDTO> fromEntity(List<Filme> filmes) {
        List<FilmeResponseDTO> listaFilmes = new ArrayList<>();
        filmes.forEach(filme -> listaFilmes.add(fromEntity(filme)));
        return listaFilmes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(String duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
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

    public Avaliacao_enum getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao_enum avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public enum Avaliacao_enum {
        OBRAPRIMA("Obra-Prima"),
        RECOMENDADO("Recomendado"),
        REGULAR("Regular");

        private final String descricao;

        Avaliacao_enum(String descricao) {
            this.descricao = descricao;
        }

        @JsonValue
        public String getDescricao() {
            return descricao;
        }
    }
}
