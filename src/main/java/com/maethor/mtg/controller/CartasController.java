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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maethor.mtg.entity.Album;
import com.maethor.mtg.entity.Carta;
import com.maethor.mtg.service.AlbumService;
import com.maethor.mtg.service.CartasService;

/* api
 | Nombre						Método	Ruta
 + ---------------------------+-------+-------
 | GET PAGINA FROM ALBUM		GET 	¬/collector/cartas/album/{id}/{page}
 | GET ALL CARTAS FROM ALBUM	GET 	¬/collector/cartas/album/{id}/all
 | PUT CARTA IN ALBUM			PUT		¬/collector/cartas/album/{id}
 | DELETE CARTA					DELETE 	¬/collector/cartas/n/{id}
 | GET X CARTAS ALEATORIAS		GET 	¬/collector/cartas/welcome/{x}
 +----------------------------+-------+-------
 * Devuelve: Carta ó Lista de Cartas
 */

@RestController
@RequestMapping("/collector/cartas")
@CrossOrigin(origins = { "*" })
public class CartasController {

	@Autowired
	private AlbumService albumService;

	@Autowired
	private CartasService cartasService;

	@GetMapping("/album/{id}/{page}")
	public ResponseEntity<Object> getPaginaFromAlbum(@PathVariable Integer id, @PathVariable Integer page,
			@Nullable @RequestParam Integer size) {

		Album album = albumService.getAlbum(id);
		if (album == null) {
			String respuesta = "El album no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		Page<Carta> paginaCartas = cartasService.getCartasFromAlbum(album, page, size);

		if (page == null) {
			String respuesta = "La página solicitada no existe en este album";
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(paginaCartas, HttpStatus.OK);
	}

	@GetMapping("/album/{id}/all")
	public ResponseEntity<Object> getAllCartasFromAlbum(@PathVariable Integer id) {
		Album album = albumService.getAlbum(id);

		if (album == null) {
			String respuesta = "El album no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}
		List<Carta> cartas = cartasService.getAllCartasFromAlbum(album);

		if (cartas == null) {
			String respuesta = "El album seleccionado no posee cartas";
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cartas, HttpStatus.OK);

	}

	@PutMapping("/album/{id}")
	public ResponseEntity<Object> addCartaToAlbum(@RequestParam String carta, @PathVariable("id") Integer albumId,
			@RequestParam("n") @Nullable Integer amount) {

		Album album = albumService.getAlbum(albumId);
		if (album == null) {
			String respuesta = "El usuario no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}
		if (amount == null) {
			amount = 1;
		}
		for (int i=0; i< amount; i++) {
			cartasService.agregarCarta(carta, albumId);
		}
		return new ResponseEntity<>(carta, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/n/{id}")
	public ResponseEntity<Object> deleteCartaFromAlbum(@PathVariable("id") String carta) {

		int id_carta = Integer.valueOf(carta);

		Carta _carta = cartasService.getCarta(id_carta);
		if (_carta == null) {
			String respuesta = "La carta no existe";
			return new ResponseEntity<>(respuesta, HttpStatus.PRECONDITION_FAILED);
		}

		cartasService.eliminarCarta(id_carta);
		return null;
	}

	@GetMapping("/welcome/{q}")
	public ResponseEntity<List<String>> getWelcomePage(@PathVariable("q") Integer cantidad) {
		return new ResponseEntity<>(cartasService.getCartasAleatorias(cantidad), HttpStatus.OK);
	}
}
