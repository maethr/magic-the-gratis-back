package com.alolorsus.collector.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alolorsus.collector.entity.Usuario;
import com.alolorsus.collector.service.UsuarioService;

@RestController
@RequestMapping("/collector")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	Map<String, Object> response = new HashMap<>();
	/*************************************************************************************************************************************/

//	-login
//	-ver mis datos
//	-ver mis viajes
//	-ver detalles del viaje

	/**
	 * Lista de Usuarios
	 ***********************************************************************************************************************************/

	@GetMapping("/usuarios")
	public List<Usuario> listaDeUsuarios() {
		return this.usuarioService.findAll();
	}
	
	@GetMapping("/usuarios/page/{pagina}")
	public Page<Usuario> listaDeUsuarios(@PathVariable Integer pagina) {
		Pageable pageable = PageRequest.of(pagina, 7);
		return this.usuarioService.findAll(pageable);
	}

	/**
	 * Get Usuario
	 ***********************************************************************************************************************************/

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable String username) {
		Usuario usuario = null;
		try {
			usuario = this.usuarioService.findByUsername(username);
			System.out.println("TODO BIEN GET USUARIO");
		} catch (DataAccessException dae) {
			this.response.put("mensaje", "Error de acceso a la base de datos");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.NOT_FOUND);
		}
		if (usuario == null) {
			this.response.put("mensaje",
					"El usuario con el Id ".concat(username.concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	/**
	 * Get Login
	 ***********************************************************************************************************************************/

	@PostMapping("/usuarios/login")

	public ResponseEntity<?> usuarioLogin(@RequestParam String user, @RequestParam String pass) {

		//pass = StringHash.getHashString(pass);
		Usuario usuario = null;
		try {
			usuario = this.usuarioService.findByUsernameAndPassword(user, pass);
		} catch (DataAccessException dae) {
			this.response.put("mensaje", "Error de acceso a la base de datos");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.NOT_FOUND);
		}
		if (usuario == null) {
			this.response.put("mensaje", "El usuario de ATOS ".concat(user.concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.NOT_FOUND);
		}
		this.response.put("mensaje", "Acceso concedido");
		this.response.put("usuario", usuario);
		return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.OK);
	}

	/**
	 * Post create nuevo Usuario
	 ***********************************************************************************************************************************/

	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED) // 201
	public ResponseEntity<?> create(@RequestParam String username, @RequestParam String password,
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
		if (usuarioService.findByUsername(username) != null) {
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
		usuario.setCreateAt(new Date());
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

	/**
	 * Put update Usuario
	 ***********************************************************************************************************************************/

	@PutMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED) // 201
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result) {
		System.out.println("EDITAR USUARIO");
		String username = usuario.getUsername();
		Usuario usuarioSujeto = this.usuarioService.findByUsername(username);

		Usuario usuarioActualizado = null;
		if (result.hasErrors()) {
			System.out.println("ERRORES EN UPDATE USUARIO 1");
			List<String> errors = result.getFieldErrors().stream().map(error -> {
				return "El campo '" + error.getField() + "' " + error.getDefaultMessage();
			}).collect(Collectors.toList());
			this.response.put("error", errors);
			System.out.println(errors);
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.BAD_REQUEST);
		}
		if (usuarioSujeto == null) {
			System.out.println("ERRORES EN UPDATE USUARIO 2");
			this.response.put("mensaje",
					"El usuario con el Id ".concat(username.concat(" no se encuentra en el sistema")));
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.NOT_FOUND);
		}
		try {
			usuarioSujeto.setEmail(usuario.getEmail());
			usuarioSujeto.setPassword(usuario.getPassword());
			usuarioSujeto.setNombre(usuario.getNombre());
			usuarioActualizado = this.usuarioService.save(usuarioSujeto);

		} catch (DataAccessException dae) {
			System.out.println("ERRORES EN UPDATE USUARIO 3");
			this.response.put("mensaje", "Error al actualizar el usuario");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		this.response.put("mensaje", "Usuario editado y actualizado en la base de datos!");
		this.response.put("usuario", usuarioActualizado);
		return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.CREATED);
	}

	/**
	 * Delete Usuario
	 ***********************************************************************************************************************************/

	@DeleteMapping("/usuario")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	public ResponseEntity<?> delete(@RequestParam String user) {
		try {
			this.usuarioService.delete(user);
		} catch (DataAccessException dae) {
			this.response.put("mensaje", "Error al eliminar el usuario");
			this.response.put("error", dae.getMessage().concat(" ").concat(dae.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		this.response.put("mensaje", "Usuario eliminado de la base de datos");
		return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.OK);
	}

	/**************************************************************************************************************************************************/

}
