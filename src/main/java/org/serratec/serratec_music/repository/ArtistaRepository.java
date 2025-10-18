package org.serratec.serratec_music.repository;

import org.serratec.serratec_music.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

}
