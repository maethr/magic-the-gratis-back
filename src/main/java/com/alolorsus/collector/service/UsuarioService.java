package com.alolorsus.collector.service;

import java.util.List;

import com.alolorsus.collector.entity.Usuario;

public interface UsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario findById(String username);
	
	// public Usuario findByEmail();
	
	public Usuario save(Usuario usuario);
	
	public void delete(String username);
}
