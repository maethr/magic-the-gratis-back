package com.alolorsus.collector.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@PostMapping("/usuario")
	public ResponseEntity<?> nuevo(@Valid @RequestBody Usuario usuario, BindingResult resultado) {
		Usuario usuarioCreado;
		Map<String, Object> respuesta = new HashMap<>();
		
		// Si usuario recibido no es valido
		if(resultado.hasErrors()) {
			List<String> errors = resultado.getFieldErrors().stream()
			.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
			.collect(Collectors.toList());
			respuesta.put("errores", errors);
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
		// Si existe un usuario con el mismo username
		try {
			if(usuarioService.findById(usuario.getUsername()) != null) {
				throw new Exception();
			}
			usuarioCreado = usuarioService.save(usuario);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ya existe este nombre de usuario");
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		// Si se ha creado correctamente
		respuesta.put("mensaje", "Usuario creado con éxito");
		respuesta.put("usuario", usuarioCreado);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

}
