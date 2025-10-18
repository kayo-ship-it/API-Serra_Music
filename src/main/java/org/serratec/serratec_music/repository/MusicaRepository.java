package org.serratec.serratec_music.repository;

import org.serratec.serratec_music.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

}
