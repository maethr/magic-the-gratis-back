package com.alolorsus.collector.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alolorsus.collector.entity.Album;

@Repository
public interface AlbumDao extends JpaRepository<Album, Integer>{

	@Query("select a from albums a where nombre like ?1")
	public Album findByNombre (String nombre);
	
	@Query("select a from albums a where usuario like ?1")
	public Page<Album> findByUsuario(String usuario, Pageable pageable);
	
}
