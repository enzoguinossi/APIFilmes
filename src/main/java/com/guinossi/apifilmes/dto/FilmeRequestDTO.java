package com.guinossi.apifilmes.dto;

import com.guinossi.apifilmes.entity.Filme;
import jakarta.validation.constraints.*;

public class FilmeRequestDTO {
    @NotBlank
    private String titulo;
    @NotNull
    @Min(value = 1888, message = "O ano deve ser superior a 1888!")
    @Max(value = 2040, message = "O ano deve ser menor que 2040!")
    private Integer anoLancamento;
    @NotBlank(message = "O genero deve ser preencido!")
    private String genero;
    @NotBlank(message = "O diretor deve ser preenchido!")
    private String diretor;
    @NotBlank
    private String idioma;
    @NotNull
    @Min(1)
    @Max(600)
    private Integer duracaoMinutos;
    @NotBlank
    @Size(min = 1, max = 2)
    private String classificacaoIndicativa;
    @NotNull
    @Min(0)
    @Max(10)
    private Double nota;

    public FilmeRequestDTO() {
    }

    static public Filme toEntity(FilmeRequestDTO filmeRequestDTO) {
        return new Filme(filmeRequestDTO);
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
}
