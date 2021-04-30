package com.alolorsus.collector.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alolorsus.collector.entity.Usuario;
import com.alolorsus.collector.service.UsuarioService;

@RestController
@RequestMapping("/album-collector/api")
@CrossOrigin(origins = { "*" })
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuario")
	public List<Usuario> lista() {
		return usuarioService.findAll();
	}

}
