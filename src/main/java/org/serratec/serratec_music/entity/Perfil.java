package org.serratec.serratec_music.entity;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "perfil")
public class Perfil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "perfil_id")
	@Schema(description = "ID do perfil")
	private Long id;
	
	@NotBlank
	@Size(max = 15)
	@Column(name = "telefone", nullable = false, length = 15)
	@Schema(description = "Telefone do usuário")
	private String telefone;
	
	@NotNull
	@Column(name = "data_nascimento", nullable = false)
	@Schema(description = "Data de nascimento do usuário")
	private LocalDate dataNascimento;
	
	@OneToOne(mappedBy = "perfil")
	private Usuario usuario;
	
	
	public Perfil() {
		super();
	}


	public Perfil(Long id, String telefone, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
