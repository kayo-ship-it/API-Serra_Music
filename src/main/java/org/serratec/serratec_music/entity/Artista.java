package org.serratec.serratec_music.entity;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "artista")
public class Artista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_artista")
	@Schema(description = "ID do artista")
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "nome", nullable = false, length = 100)
	@Schema(description = "Nome do artista")
	private String nome;
	
	@NotBlank
	@Size(max = 50)
	@Column(name = "nacionalidade", nullable = false, length = 50)
	@Schema(description = "Nacionalidade do artista")
	private String nacionalidade;
	
	@ManyToMany(mappedBy = "artistas")
	private List<Musica> musicas;


	public Artista() {
		super();
	}

	public Artista(Long id, String nome, String nacionalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
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

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	
	
}
