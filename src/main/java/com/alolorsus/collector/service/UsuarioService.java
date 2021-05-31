package com.alolorsus.collector.service;

import java.util.List;

import com.alolorsus.collector.entity.Usuario;

public interface UsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario findByUsername(String username);
	
	public Usuario findByUsernameAndPassword(String username, String password);
	
	// public Usuario findByEmail();
	
	public Usuario save(Usuario usuario);
	
	public void delete(String username);
}
