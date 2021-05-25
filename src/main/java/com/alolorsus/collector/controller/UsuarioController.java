package com.alolorsus.collector.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alolorsus.collector.entity.Usuario;
import com.alolorsus.collector.service.UsuarioService;

@RestController
@RequestMapping("/collector")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/usuario")
	public List<Usuario> lista() {
		return usuarioService.findAll();
	}

	@PostMapping("/usuario")
	public ResponseEntity<Object> nuevo (@RequestParam String username, @RequestParam String password,
			@RequestParam String nombre, @RequestParam String email) {
	
		Object respuesta;
		HttpStatus status;

		// Si la petición es incorrecta
		if (username == null || password == null || email == null) {
			respuesta = "Faltan datos del usuario";
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<>(respuesta, status);
		}
		
		// Si el usuario no es valido
		if (usuarioService.findById(username) != null) {
			respuesta = "El usuario ya existe";
			status = HttpStatus.PRECONDITION_FAILED;
			return new ResponseEntity<>(respuesta, status);
		}
		
		// Crea el usuario con los valores
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setEmail(email);
		usuario.setNombre(nombre);
		respuesta = usuarioService.save(usuario);
		
		// Si no se ha conseguido crear
		if (respuesta == null) {
			respuesta = "El usuario no se ha podido crear";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(respuesta, status);
		}
		
		// Si todo OK
		status = HttpStatus.CREATED;
		return new ResponseEntity<>(respuesta, status);
	}
	
	// buscar usuarios
	
	// obtener usuario
	
	// borrar usuario
}
