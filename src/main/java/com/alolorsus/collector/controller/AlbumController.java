package com.alolorsus.collector.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alolorsus.collector.entity.Album;
import com.alolorsus.collector.entity.Carta;
import com.alolorsus.collector.entity.Usuario;
import com.alolorsus.collector.service.AlbumService;
import com.alolorsus.collector.service.UsuarioService;

@RestController
@RequestMapping("/collector")
@CrossOrigin(origins = { "*" })
public class AlbumController {

	@Autowired
	private AlbumService albumService;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/album/{id}")
	public ResponseEntity<Object> getAlbum(@PathVariable Integer id) {

		// Obtener la página
		Album album = albumService.getAlbum(id);

		// Si el album no se ha podido obtener
		if (album == null) {
			String respuesta = "No existe el album solicitado";
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		}

		// Si todo OK
		return new ResponseEntity<>(album, HttpStatus.OK);
	}

	@GetMapping("/user/{user}/albums")
	public ResponseEntity<Object> getAlbumsFromUsuario(@PathVariable String user,
			@Nullable @RequestParam Integer page) {

		// Si el usuario no existe
		Usuario usuario = usuarioService.findByUsername(user);
		if (user == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		// Obtener la página
		Page<Album> albums = albumService.getAlbumsFromUser(usuario, page);

		// Si el album no se ha podido obtener
		if (albums == null) {
			String respuesta = "No existen los albums solicitados";
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		}

		// Si todo OK
		return new ResponseEntity<>(albums, HttpStatus.OK);
	}
	
	@GetMapping("/user/{user}/albums/all")
	public ResponseEntity<Object> getAllAlbumsFromUsuario(@PathVariable String user) {

		// Si el usuario no existe
		Usuario usuario = usuarioService.findByUsername(user);
		if (user == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		// Obtener la lista
		List<Album> albums = albumService.getAllAlbumsFromUser(usuario);

		// Si el album no se ha podido obtener
		if (albums == null) {
			String respuesta = "No existen los albums solicitados";
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		}

		// Si todo OK
		return new ResponseEntity<>(albums, HttpStatus.OK);
	}

	@GetMapping("/user/{user}/albums/num")
	public ResponseEntity<Object> countAlbumsFromUsuario(@PathVariable String user) {

		// Si el usuario no existe
		Usuario usuario = usuarioService.findByUsername(user);
		if (user == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		Integer n = albumService.countAlbumsFromUser(usuario);

		return new ResponseEntity<>(n, HttpStatus.OK);
	}

	@PostMapping("/album")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createAlbum(@RequestParam String nombre, @RequestParam String usuario) {

		// Si el usuario no existe
		Usuario user = usuarioService.findByUsername(usuario);
		if (user == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		// Crear el album
		Album album = albumService.crearAlbum(nombre, user);

		// Si el album no se ha podido crear
		if (album == null) {
			String respuesta = "El album no ha sido creado";
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Si todo OK
		return new ResponseEntity<>(album, HttpStatus.CREATED);
	}

	@GetMapping("/album/{id}/{page}")
	public ResponseEntity<Object> getPaginaFromAlbum(@PathVariable Integer id, @PathVariable Integer page) {

		// Si el album no existe
		Album album = albumService.getAlbum(id);
		if (album == null) {
			String respuesta = "El album no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		// Obtener la página
		Page<Carta> paginaCartas = albumService.getCartasFromAlbum(album, page);

		// Si la página no existe
		if (page == null) {
			String respuesta = "La página solicitada no existe en este album";
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(paginaCartas, HttpStatus.OK);
	}

	@PutMapping("/album/{id}")
	public ResponseEntity<Object> addCartaToAlbum(@RequestParam String carta, @PathVariable Integer id) {
		System.out.println("a");
		// Si el album no existe
		Album album = albumService.getAlbum(id);
		if (album == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		// Guardar la carta
		Carta _carta = albumService.agregarCarta(carta, id);

		// Si no se ha podido guardar
		if (_carta == null) {
			String respuesta = "La carta no ha sido guardada";
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Si todo OK
		return new ResponseEntity<>(_carta, HttpStatus.ACCEPTED);
	}

}
