package com.maethor.mtg.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maethor.mtg.dao.UsuarioDao;
import com.maethor.mtg.entity.Usuario;
import com.maethor.mtg.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}
	
	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return this.usuarioDao.findAll(pageable);
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
		if (usuario.getRol() == null) {
			usuario.setRol("user");
		}
		return usuarioDao.save(usuario);
	}

	@Override
	public void delete(String username) {
		usuarioDao.deleteById(username);
		
	}

}
