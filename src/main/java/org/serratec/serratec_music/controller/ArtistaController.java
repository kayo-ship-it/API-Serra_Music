package org.serratec.serratec_music.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratec_music.entity.Artista;
import org.serratec.serratec_music.repository.ArtistaRepository;
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
@RequestMapping("/artistas")
public class ArtistaController {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@GetMapping
	public ResponseEntity<List<Artista>> listarArtistas() {
		return ResponseEntity.ok(artistaRepository.findAll());
	}
	
	@Operation(summary = "Buscar artista por ID", description = "Retorna um artista específico com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Artista encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Artista não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Artista> buscarPorId(@PathVariable Long id) {
		Optional<Artista> artista = artistaRepository.findById(id);

		if (artista.isPresent()) {
			return ResponseEntity.ok(artista.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Artista criar(@Valid @RequestBody Artista artista) {
		return artistaRepository.save(artista);
	}
	
	@PostMapping("/lista")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Artista> criarLista(@Valid @RequestBody List<Artista> artistas) {
		return artistaRepository.saveAll(artistas);
	}
	
	@Operation(summary = "Atualizar artista por ID", description = "Atualiza um artista existente com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Artista atualizado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Artista não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Artista> atualizar(@PathVariable Long id, @Valid @RequestBody Artista artista) {
		if (!artistaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		artista.setId(id);
		Artista artistaAtualizado = artistaRepository.save(artista);
		return ResponseEntity.ok(artistaAtualizado);
	}
	
	@Operation(summary = "Deletar artista por ID", description = "Deleta um artista específico com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Artista deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Artista não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!artistaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		artistaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
