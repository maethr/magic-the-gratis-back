package com.maethor.mtg.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maethor.mtg.entity.Album;
import com.maethor.mtg.entity.Carta;

@Repository
public interface CartaDao extends JpaRepository<Carta, Integer>{
	
	@Query("select c from cartas c where album = ?1")
	public List<Carta> findAllByAlbum(Album album);
	
	@Query("select c from cartas c where album = ?1")
	public Page<Carta> findByAlbum(Album album, Pageable pagina);
	
	@Query("select count(c) from cartas c where album_id = ?1")
	public int countCartasAlbum(int id);
	
}
