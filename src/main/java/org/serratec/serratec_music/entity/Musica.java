package org.serratec.serratec_music.entity;

import java.util.List;

import org.serratec.serratec_music.Enum.GeneroMusical;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "musica")
public class Musica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_musica")
	@Schema(description = "ID da música")
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "titulo", nullable = false, length = 100)
	@Schema(description = "Título da música")
	private String titulo;
	
	@NotNull
	@Column(name = "minutos", nullable = false)
	@Schema(description = "Duração da música em minutos")
	private Integer minutos;
	
	@Enumerated(EnumType.STRING)
	private GeneroMusical genero;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
	    name = "musica_artista", // nome da tabela intermediária
	    joinColumns = @JoinColumn(name = "id_musica"), // FK de Musica
	    inverseJoinColumns = @JoinColumn(name = "id_artista") // FK de Artista
	)
	private List<Artista> artistas;

	
	public Musica() {
		super();
	}

	public Musica(Long id, String titulo, Integer minutos, GeneroMusical genero, List<Artista> artistas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.minutos = minutos;
		this.genero = genero;
		this.artistas = artistas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getMinutos() {
		return minutos;
	}

	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}

	public GeneroMusical getGenero() {
		return genero;
	}

	public void setGenero(GeneroMusical genero) {
		this.genero = genero;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}
	
	

}
