package com.guinossi.apifilmes.dto;

import com.guinossi.apifilmes.entity.Filme;
import jakarta.validation.constraints.*;

public class FilmeRequestDTO {
    @NotBlank
    private String titulo;
    @NotNull
    @Min(value = 1888, message = "O ano deve ser superior ou igual a 1888!")
    @Max(value = 2040, message = "O ano deve ser menor ou igual a 2040!")
    private Integer anoLancamento;
    @NotBlank(message = "O genero deve ser preenchido!")
    @Pattern(regexp = "[\\p{L}\\p{M}\\s.'-]*", message = "O genero deve conter apenas letras!")
    private String genero;
    @NotBlank(message = "O diretor deve ser preenchido!")
    @Pattern(regexp = "[\\p{L}\\p{M}\\s.'-]*", message = "O diretor deve conter apenas letras!")
    private String diretor;
    @NotBlank(message = "O idioma deve ser preenchido!")
    @Pattern(regexp = "[\\p{L}\\p{M}\\s.'-]*", message = "O idioma deve conter apenas letras!")
    private String idioma;
    @NotNull(message = "A duração deve ser preenchida!")
    @Min(value = 1, message = "A duração deve ser maior ou igual a 1 minuto!")
    @Max(value = 600, message = "A duração deve ser menor ou igual a 600 minutos!")
    private Integer duracaoMinutos;
    @NotBlank(message = "A classificação indicativa deve ser preenchida!")
    @Size(min = 1, max = 2, message = "A classificação indicativa deve ter entre 1 e 2 caracteres!")
    private String classificacaoIndicativa;
    @NotNull(message = "A nota deve ser preenchida!")
    @Min(value = 0, message = "A nota deve ser maior ou igual a 0!")
    @Max(value = 10, message = "A nota deve ser menor ou igual a 10!")
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
