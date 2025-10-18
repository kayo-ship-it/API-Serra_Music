package org.serratec.serratec_music.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratec_music.entity.Usuario;
import org.serratec.serratec_music.repository.UsuarioRepository;
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
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
			})
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario criar(@Valid @RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Operation(summary = "Atualizar usuário por ID", description = "Atualiza um usuário específico com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
			})
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		if (!usuarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		usuario.setId(id);
		usuario = usuarioRepository.save(usuario);
		
		return ResponseEntity.ok(usuario);
	}
	
	@Operation(summary = "Deletar usuário por ID", description = "Deleta um usuário específico com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!usuarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		usuarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
