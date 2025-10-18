package org.serratec.serratec_music.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "playlist")
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_playlist")
	@Schema(description = "ID da playlist")
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "nome", nullable = false, length = 100)
	@Schema(description = "Nome da playlist")
	private String nome;
	
	@Size(max = 255)
	@Column(name = "descricao", length = 255)
	@Schema(description = "Descrição da playlist")
	private String descricao;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
	    name = "playlist_musica", // nome da tabela intermediária
	    joinColumns = @JoinColumn(name = "id_playlist"), // FK pra Playlist
	    inverseJoinColumns = @JoinColumn(name = "id_musica") // FK pra Musica
	)
	private List<Musica> musicas;

	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private Usuario usuario;
	
	public Playlist() {
		super();
	}

	public Playlist(Long id, String nome, String descricao, List<Musica> musicas, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.musicas = musicas;
		this.usuario = usuario;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
