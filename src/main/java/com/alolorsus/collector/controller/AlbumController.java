package com.alolorsus.collector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alolorsus.collector.entity.Album;
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
	
	@GetMapping("/user/{usuario}/albums")
	public Page<Album> getAlbumsUsuario (@PathVariable String usuario, @RequestParam Integer page) {
		return albumService.getAlbumsFromUser(usuario, page);
	}
	
	@PostMapping("/album")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> crearAlbum (@RequestParam String nombre, @RequestParam String usuario) {
		
		Object respuesta;
		
		// Si el usuario no existe
		Usuario user = usuarioService.findById(usuario);
		if (user == null) {
			respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}
		System.out.println(user);
		// Crear el album
		Album album = albumService.crearAlbum(nombre, user);
		
		// Si el album no se ha podido crear
		if (album == null) {
			respuesta = "El album no ha sido creado";
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// Si todo OK
		return new ResponseEntity<>(album, HttpStatus.CREATED);
	}
	
	/*@GetMapping("/user/{username}")
	public Page<Album> getUserAlbums (@PathVariable String username, @RequestParam Integer pagina) {
		return albumService.getAlbumsFromUser(username, pagina);
	}
	
	@PostMapping("/user")
	public void createAlbum (@RequestParam String username, @RequestBody Album album) {
		
		
	}
	
	@GetMapping("/album/{album_id}/{pagina}")
	public Page<Carta> getCartas (@PathVariable Integer album_id, @PathVariable Integer pagina) {
		return albumService.getCartasFromAlbum(album_id, pagina);
	}*/

}
