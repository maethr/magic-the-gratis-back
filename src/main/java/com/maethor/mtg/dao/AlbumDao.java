package com.maethor.mtg.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maethor.mtg.entity.Album;
import com.maethor.mtg.entity.Usuario;

@Repository
public interface AlbumDao extends JpaRepository<Album, Integer>{

	@Query("select a from albums a where nombre like ?1")
	public Album findByNombre (String nombre);
	
	@Query("select a from albums a where usuario like ?1")
	public List<Album> findByUsuario(Usuario usuario);
	
	@Query("select a from albums a where usuario like ?1")
	public Page<Album> findByUsuario(Usuario usuario, Pageable pageable);
	
	@Query("select count(a) from albums a where usuario like ?1")
	public Integer countAlbumsByUsuario(Usuario usuario);
	
}
