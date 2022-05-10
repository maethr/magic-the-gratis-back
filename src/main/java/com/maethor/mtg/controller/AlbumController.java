package com.maethor.mtg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maethor.mtg.entity.Album;
import com.maethor.mtg.entity.Usuario;
import com.maethor.mtg.service.AlbumService;
import com.maethor.mtg.service.UsuarioService;

/* api
 | Nombre							Método	Ruta
 + -------------------------------+-------+-------
 | GET ALBUM BY ID					GET 	¬/collector/album/n/{id}
 | GET ALBUMS FROM USUARIO PAGED	GET 	¬/collector/album/user/{user}
 | GET ALL ALBUMS FROM USUARIO		GET 	¬/collector/album/user/{user}/all
 | COUNT ALBUMS FROM USUARIO		GET		¬/collector/album/user/{user}/count
 | CREATE ALBUM						POST	¬/collector/album/
 | EDIT ALBUM						PUT 	¬/collector/album/n/{id}
 | DELETE ALBUM						DELETE	¬/collector/album/n/{id}
 | COUNT CARTAS ALBUM				GET		¬/collector/album/n/{id}/count
 + -------------------------------+-------+-------
 * Devuelve: Album ó Lista de Albumes
 */

@RestController
@RequestMapping("/collector/album")
@CrossOrigin(origins = { "*" })
public class AlbumController {

	@Autowired
	private AlbumService albumService;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/n/{id}")
	public ResponseEntity<Object> getAlbumById(@PathVariable Integer id) {
		Album album = albumService.getAlbum(id);
		if (album == null) {
			String respuesta = "No existe el album solicitado";
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(album, HttpStatus.OK);
	}

	@GetMapping("/user/{user}")
	public ResponseEntity<Object> getAlbumsFromUsuario(@PathVariable String user,
			@Nullable @RequestParam Integer page) {

		Usuario usuario = usuarioService.findByUsername(user);
		if (user == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		Page<Album> albums = albumService.getAlbumsFromUser(usuario, page);

		if (albums == null) {
			String respuesta = "No existen los albums solicitados";
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(albums, HttpStatus.OK);
	}

	@GetMapping("/user/{user}/all")
	public ResponseEntity<Object> getAllAlbumsFromUsuario(@PathVariable String user) {

		Usuario usuario = usuarioService.findByUsername(user);
		if (user == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		List<Album> albums = albumService.getAllAlbumsFromUser(usuario);

		if (albums == null) {
			String respuesta = "No existen los albums solicitados";
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(albums, HttpStatus.OK);
	}

	@GetMapping("/user/{user}/count")
	public ResponseEntity<Object> countAlbumsFromUsuario(@PathVariable String user) {

		Usuario usuario = usuarioService.findByUsername(user);
		if (user == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		Integer n = albumService.countAlbumsFromUser(usuario);

		return new ResponseEntity<>(n, HttpStatus.OK);
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createAlbum(@RequestParam String nombre, @RequestParam String usuario) {

		Usuario user = usuarioService.findByUsername(usuario);
		if (user == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		Album album = albumService.crearAlbum(nombre, user);

		if (album == null) {
			String respuesta = "El album no ha sido creado";
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(album, HttpStatus.CREATED);
	}

	@DeleteMapping("/n/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	public ResponseEntity<Object> deleteAlbum(@PathVariable(value = "id") Integer album_id) {
		Album _album = albumService.getAlbum(album_id);
		if (_album == null) {
			String respuesta = "El album no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		albumService.eliminarAlbum(_album);

		String respuesta = "Album borrado";
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

	@PutMapping("/n/{id}")
	public ResponseEntity<Object> editarAlbum(@PathVariable Integer id, @RequestParam String nombre,
			@RequestParam @Nullable String portada) {

		Album _album = albumService.getAlbum(id);
		if (_album == null) {
			String respuesta = "El album no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		Integer portadaId;
		if (portada.equals("null")) {
			portadaId = null;
		} else {
			portadaId = Integer.valueOf(portada);
		}
		_album = albumService.editarAlbum(id, nombre, portadaId, null);

		if (_album == null) {
			String respuesta = "La carta no ha sido guardada";
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(_album, HttpStatus.ACCEPTED);
	}

	@GetMapping("/album/{id}/count")
	public ResponseEntity<Object> countCartasAlbum(@PathVariable("id") int id) {
		return new ResponseEntity<>(albumService.countCartasAlbum(id), HttpStatus.OK);
	}
}
