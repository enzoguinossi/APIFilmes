package com.guinossi.apifilmes.controller;

import com.guinossi.apifilmes.dto.FilmeRequestDTO;
import com.guinossi.apifilmes.dto.FilmeResponseDTO;
import com.guinossi.apifilmes.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@Tag(name = "Filme", description = "API para gerenciamento de filmes")
public class FilmeController {
    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar filme", description = "Cria um novo filme no banco de dados.")
    public ResponseEntity<FilmeResponseDTO> criar(@RequestBody @Valid FilmeRequestDTO filmeRequestDTO) {
        return ResponseEntity.status(201).body(filmeService.criar(filmeRequestDTO));
    }

    @GetMapping
    @Operation(summary = "Listar filmes", description = "Lista todos os filmes cadastrados no banco de dados.")
    public ResponseEntity<List<FilmeResponseDTO>> listar() {
        return ResponseEntity.status(200).body(filmeService.listar());
    }

    @GetMapping("/titulo")
    @Operation(summary = "Buscar filmes por título",
            description = "Retorna os filmes cujo título contém o texto informado, ignorando maiúsculas e minúsculas.")
    public ResponseEntity<List<FilmeResponseDTO>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(filmeService.buscarPorTitulo(titulo));
    }

    @GetMapping("/genero")
    @Operation(summary = "Buscar filmes por gênero",
            description = "Retorna os filmes do gênero informado, ignorando maiúsculas e minúsculas.")
    public ResponseEntity<List<FilmeResponseDTO>> buscarPorGenero(@RequestParam String genero) {
        return ResponseEntity.ok(filmeService.buscarPorGenero(genero));
    }

    @GetMapping("/ano")
    @Operation(summary = "Buscar filmes por intervalo de anos",
            description = "Retorna os filmes lançados entre o ano inicial e o ano final informados.")
    public ResponseEntity<List<FilmeResponseDTO>> buscarPorAno(@RequestParam int inicio, @RequestParam int fim) {
        return ResponseEntity.ok(filmeService.buscarPorAno(inicio, fim));
    }

    @GetMapping("/diretor")
    @Operation(summary = "Buscar filmes por diretor",
            description = "Retorna os filmes cujo diretor contém o texto informado, ignorando maiúsculas e minúsculas.")
    public ResponseEntity<List<FilmeResponseDTO>> buscarPorDiretor(@RequestParam String diretor) {
        return ResponseEntity.ok(filmeService.buscarPorDiretor(diretor));
    }

    @GetMapping("/duracao")
    @Operation(summary = "Buscar filmes por duração",
            description = "Retorna os filmes com duração menor ou igual à informada, do lançamento mais novo para o mais antigo.")
    public ResponseEntity<List<FilmeResponseDTO>> buscarPorDuracao(@RequestParam Integer duracao) {
        return ResponseEntity.ok(filmeService.buscarPorDuracao(duracao));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar filme", description = "Altera um filme existente no banco de dados.")
    public ResponseEntity<FilmeResponseDTO> alterar(@PathVariable Long id, @RequestBody @Valid FilmeRequestDTO filmeRequestDTO) {
        return ResponseEntity.status(200).body(filmeService.alterar(id, filmeRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar filme", description = "Deleta um filme existente no banco de dados.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
