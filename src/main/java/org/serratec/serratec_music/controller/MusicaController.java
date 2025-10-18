package org.serratec.serratec_music.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratec_music.entity.Artista;
import org.serratec.serratec_music.entity.Musica;
import org.serratec.serratec_music.repository.ArtistaRepository;
import org.serratec.serratec_music.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/musica")
public class MusicaController {
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@GetMapping
	public ResponseEntity<List<Musica>> listar() {
		return ResponseEntity.ok(musicaRepository.findAll());
	}
	
	@Operation(summary = "Buscar música por ID", description = "Retorna uma música específica com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Música encontrada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Música não encontrada"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) {
		Optional<Musica> musica = musicaRepository.findById(id);

		if (musica.isPresent()) {
			return ResponseEntity.ok(musica.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Musica criar(@Valid @RequestBody Musica musica) {
	    List<Artista> artistasGerenciados = musica.getArtistas().stream()
	        .map(a -> artistaRepository.findById(a.getId())
	            .orElseThrow(() -> new RuntimeException("Artista com id " + a.getId() + " não encontrado")))
	        .toList();

	    musica.setArtistas(artistasGerenciados);

	    return musicaRepository.save(musica);
	}

	
	@PostMapping("/lista")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Musica> criarLista(@Valid @RequestBody List<Musica> musicas) {
	    List<Musica> musicasGerenciadas = musicas.stream().map(musica -> {
	        List<Artista> artistasGerenciados = musica.getArtistas().stream()
	            .map(a -> artistaRepository.findById(a.getId())
	                .orElseThrow(() -> new RuntimeException("Artista com id " + a.getId() + " não encontrado")))
	            .toList();
	        musica.setArtistas(artistasGerenciados);
	        return musica;
	    }).toList();

	    return musicaRepository.saveAll(musicasGerenciadas);
	}

	
	@Operation(summary = "Atualizar música por ID", description = "Atualiza uma música existente com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Música atualizada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Música não encontrada"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Musica> atualizar(@PathVariable Long id, @Valid @RequestBody Musica musica) {
		if (!musicaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		musica.setId(id);
		Musica musicaAtualizada = musicaRepository.save(musica);
		return ResponseEntity.ok(musicaAtualizada);
	}
	
	@Operation(summary = "Deletar música por ID", description = "Deleta uma música específica com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Música deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Música não encontrada"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!musicaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		musicaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
	
