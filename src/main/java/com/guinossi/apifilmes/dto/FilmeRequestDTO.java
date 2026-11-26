package com.guinossi.apifilmes.dto;

import com.guinossi.apifilmes.entity.Filme;
import jakarta.validation.constraints.*;

public class FilmeRequestDTO {
    @NotBlank
        private String titulo;
    @NotNull
    @Min(1888) @Max(2040)
        private Integer anoLancamento;
    @NotBlank
        private String genero;
    @NotBlank
        private String diretor;
    @NotBlank
        private String idioma;
    @NotNull
    @Min(1) @Max(600)
        private Integer duracaoMinutos;
    @NotBlank
    @Size(min = 1, max = 2)
        private String classificacaoIndicativa;
    @NotNull
    @Min(0) @Max(10)
        private Double nota;

    public FilmeRequestDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public Double getNota() {
        return nota;
    }

    static public Filme toEntity(FilmeRequestDTO filmeRequestDTO) {
        return new Filme(filmeRequestDTO);
    }
}
