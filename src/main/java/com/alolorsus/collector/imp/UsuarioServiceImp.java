package com.alolorsus.collector.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alolorsus.collector.dao.UsuarioDao;
import com.alolorsus.collector.entity.Usuario;
import com.alolorsus.collector.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findById(username).orElse(null);
	}
	
	@Override
	public Usuario findByUsernameAndPassword(String username, String password) {
		return usuarioDao.findByUsernameAndPassword(username, password);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public void delete(String username) {
		usuarioDao.deleteById(username);
		
	}

}
