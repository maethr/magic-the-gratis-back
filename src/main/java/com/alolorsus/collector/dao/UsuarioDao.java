package com.alolorsus.collector.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alolorsus.collector.entity.Usuario;

@Repository
public interface UsuarioDao extends CrudRepository<Usuario, String>{

	@Query("select u from usuarios u where username=?1 and password=?2")
	public Usuario findByUsernameAndPassword(String username, String password);

	@Query("select u from usuarios u")
	public Page<Usuario> findAll(Pageable pageable);
	
}
