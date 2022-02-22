package com.maethor.mtg.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.maethor.mtg.entity.Usuario;

public interface UsuarioService {
	
	public List<Usuario> findAll();

	public Page<Usuario> findAll(Pageable pageable);

	public Usuario findByUsername(String username);
	
	public Usuario findByUsernameAndPassword(String username, String password);
	
	// public Usuario findByEmail();
	
	public Usuario save(Usuario usuario);
	
	public void delete(String username);
}
