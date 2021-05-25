package com.alolorsus.collector.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alolorsus.collector.entity.Carta;

@Repository
public interface CartaDao extends JpaRepository<Carta, Integer>{
	
	@Query("select c from cartas c where album = ?1")
	public Page<Carta> findByAlbum(Integer albumId, Pageable pagina);
	
	

}
