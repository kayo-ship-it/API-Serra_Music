package org.serratec.serratec_music.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratec_music.entity.Perfil;
import org.serratec.serratec_music.repository.PerfilRepository;
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
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@GetMapping
	public ResponseEntity<List<Perfil>> listar() {
		return ResponseEntity.ok(perfilRepository.findAll());
	}
	
	@Operation(summary = "Buscar perfil por ID", description = "Retorna um perfil específico com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Perfil encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Perfil não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
		})
	@GetMapping("/{id}")
	public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
		Optional<Perfil> perfil = perfilRepository.findById(id);

		if (perfil.isPresent()) {
			return ResponseEntity.ok(perfil.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Perfil criar(@Valid @RequestBody Perfil perfil) {
		return perfilRepository.save(perfil);
	}
	
	@Operation(summary = "Atualizar perfil por ID", description = "Atualiza um perfil específico com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Perfil atualizado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Perfil não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
		})
	@PutMapping("/{id}")
	public ResponseEntity<Perfil> atualizar(@PathVariable Long id, @Valid @RequestBody Perfil perfil) {
		if (!perfilRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		perfil.setId(id);
		Perfil perfilAtualizado = perfilRepository.save(perfil);
		return ResponseEntity.ok(perfilAtualizado);
	}
	
	@Operation(summary = "Deletar perfil por ID", description = "Deleta um perfil específico com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Perfil deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Perfil não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!perfilRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		perfilRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	

}
