package org.serratec.serratec_music.entity;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	@Schema(description = "ID do usuário")
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "nome", nullable = false, length = 100)
	@Schema(description = "Nome do usuário")
	private String nome;
	
	@NotBlank
	@Size(max = 100)
	@Email
	@Column(name = "email", nullable = false, length = 100, unique = true)
	@Schema(description = "Email do usuário")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id")
	private Perfil perfil;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Playlist> playlists;
	

	public Usuario() {
		super();
	}


	public Usuario(Long id, String nome, String email, Perfil perfil, List<Playlist> playlists) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.perfil = perfil;
		this.playlists = playlists;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Perfil getPerfil() {
		return perfil;
	}


	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}


	public List<Playlist> getPlaylists() {
		return playlists;
	}


	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	
	
	

}
