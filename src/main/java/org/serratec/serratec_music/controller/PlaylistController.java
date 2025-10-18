package org.serratec.serratec_music.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratec_music.entity.Playlist;
import org.serratec.serratec_music.repository.PlaylistRepository;
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
@RequestMapping("/playlists")
public class PlaylistController {
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@GetMapping
	public ResponseEntity<List<Playlist>> listarPlaylists() {
		return ResponseEntity.ok(playlistRepository.findAll());
	}
	
	@Operation(summary = "Buscar playlist por ID", description = "Retorna uma playlist específica com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Playlist encontrada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Playlist não encontrada"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
		Optional<Playlist> playlist = playlistRepository.findById(id);
		
		if (playlist.isPresent()) {
			return ResponseEntity.ok(playlist.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Playlist criarPlaylist(@Valid @RequestBody Playlist playlist) {
		return playlistRepository.save(playlist);
	}
	
	@PostMapping("/lista")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Playlist> criarListaPlaylists(@Valid @RequestBody List<Playlist> playlists) {
		return playlistRepository.saveAll(playlists);
	}

	@Operation(summary = "Atualizar playlist por ID", description = "Atualiza uma playlist específica com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Playlist atualizada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Playlist não encontrada"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Playlist> atualizarPlaylist(@PathVariable Long id, @Valid @RequestBody Playlist playlist) {
		if (!playlistRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		playlist.setId(id);
		Playlist playlistAtualizada = playlistRepository.save(playlist);
		return ResponseEntity.ok(playlistAtualizada);
	}
	
	@Operation(summary = "Deletar playlist por ID", description = "Deleta uma playlist específica com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Playlist deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Playlist não encontrada"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPlaylist(@PathVariable Long id) {
		if (!playlistRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		playlistRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
