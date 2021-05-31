package com.alolorsus.collector.controller;

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
	

	@GetMapping("/user/{user}/albums")
	public ResponseEntity<Object> getAlbumsUsuario(@PathVariable String user, @Nullable @RequestParam Integer page) {
		
		// Si el usuario no existe
		Usuario usuario = usuarioService.findByUsername(user);
		if (user == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		// Obtener la página
		Page<Album> albums = albumService.getAlbumsFromUser(usuario, page);

		// Si el album no se ha podido crear
		if (albums == null) {
			String respuesta = "No existen los albums solicitados";
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		}

		// Si todo OK
		return new ResponseEntity<>(albums, HttpStatus.OK);
	}

	@PostMapping("/album")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> crearAlbum(@RequestParam String nombre, @RequestParam String usuario) {

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
	public ResponseEntity<Object> getPaginaAlbum(@PathVariable Integer id, @PathVariable Integer page) {

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

	@PutMapping("/album")
	public ResponseEntity<Object> addCartaAlbum(String carta, Integer album) {

		// Si el album no existe
		Album _album = albumService.getAlbum(album);
		if (_album == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		// Guardar la carta
		Carta _carta = albumService.agregarCarta(carta, album);

		// Si no se ha podido guardar
		if (_carta == null) {
			String respuesta = "La carta no ha sido guardada";
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Si todo OK
		return new ResponseEntity<>(_carta, HttpStatus.ACCEPTED);
	}

	/*
	 * @GetMapping("/user/{username}") public Page<Album> getUserAlbums
	 * (@PathVariable String username, @RequestParam Integer pagina) { return
	 * albumService.getAlbumsFromUser(username, pagina); }
	 * 
	 * @PostMapping("/user") public void createAlbum (@RequestParam String
	 * username, @RequestBody Album album) {
	 * 
	 * 
	 * }
	 * 
	 * @GetMapping("/album/{album_id}/{pagina}") public Page<Carta> getCartas
	 * (@PathVariable Integer album_id, @PathVariable Integer pagina) { return
	 * albumService.getCartasFromAlbum(album_id, pagina); }
	 */

}
