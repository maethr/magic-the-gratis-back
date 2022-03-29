package com.maethor.mtg.service.imp;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maethor.mtg.dao.AlbumDao;
import com.maethor.mtg.dao.CartaDao;
import com.maethor.mtg.entity.Album;
import com.maethor.mtg.entity.Carta;
import com.maethor.mtg.entity.Juego;
import com.maethor.mtg.entity.Usuario;
import com.maethor.mtg.service.AlbumService;

@Service
public class AlbumServiceImp implements AlbumService {

	private final int albumsPorPagina = 16;
	private final int cartasPorPagina = 9;

	@Autowired
	private AlbumDao albumDao;

	@Autowired
	private CartaDao cartaDao;

	@Override
	public Page<Album> getAlbumsFromUser(Usuario usuario, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, albumsPorPagina);
		return albumDao.findByUsuario(usuario, pag);
	}

	@Override
	public List<Carta> getAllCartasFromAlbum(Album album) {
		return cartaDao.findAllByAlbum(album);
	}
	
	@Override
	public Page<Carta> getCartasFromAlbum(Album album, Integer pagina, Integer size) {
		if (pagina == null)
			pagina = 0;
		if (size == null)
			size = cartasPorPagina;
		Pageable pag = PageRequest.of(pagina, size);
		return cartaDao.findByAlbum(album, pag);
	}
	
	@Override
	public Album crearAlbum(String nombre, Usuario usuario) {
		Album album = new Album();
		album.setNombre(nombre);
		album.setUsuario(usuario);
		album.setJuego(Juego.Magic);
		return albumDao.save(album);
	}

	@Override
	public Carta agregarCarta(String scryfallId, Integer albumId, Integer amount) {
		List<Carta> cartasRepetidas = cartaDao.findByAlbumAndScryfallId(albumId, scryfallId);
		// Coger lista de cartas repetidas
		if (cartasRepetidas.size() > 0) {
			Carta carta = cartasRepetidas.get(0);
			carta.setAmount(carta.getAmount() + amount);
			// Si hay repetidas, sumamos la cantidad actual a la primera repetida encontrada
			
			for(int i = 1; i < cartasRepetidas.size(); i++) {
				Carta c = cartasRepetidas.get(i);
				carta.setAmount(carta.getAmount() + c.getAmount());
				cartaDao.delete(c);
				// Si está más de una vez repetida, borramos todas las demás apariciones,
				// sumando sus cantidades a la primera aparición, y dejamos solo la primera
			}
			return cartaDao.save(carta);
			// Guardamos la carta encontrada con su nueva cantidad
		}
		
		Carta carta = new Carta();
		carta.setScryfallId(scryfallId);
		carta.setAmount(amount);
		// Si no está repetida la carta, la creamos
		
		Album album = albumDao.findById(albumId).orElseThrow();
		if (album != null) {
			carta.setAlbum(album);
		}
		return cartaDao.save(carta);
	}

	@Override
	public Album getAlbum(Integer album_id) {
		return albumDao.findById(album_id).orElseThrow();
	}

	@Override
	public void eliminarCarta(Integer carta_id) {
		cartaDao.deleteById(carta_id);
	}

	@Override
	public void eliminarAlbum(Album album) {

		List<Carta> cartasToRemove = cartaDao.findAllByAlbum(album);
		for (Carta carta: cartasToRemove) {
			cartaDao.delete(carta);
		}
		albumDao.delete(album);
	}


	@Override
	public int countAlbumsFromUser(Usuario usuario) {
		return albumDao.countAlbumsByUsuario(usuario);
	}

	@Override
	public int countCartasAlbum(int id) {
		return cartaDao.countCartasAlbum(id);
	}

	@Override
	public List<Album> getAllAlbumsFromUser(Usuario usuario) {
		return albumDao.findByUsuario(usuario);
	}

	@Override
	public Album editarAlbum(Integer id, String nombre, Integer portada) {
		Album album = albumDao.findById(id).orElseThrow();
		album.setNombre(nombre);
		album.setPortada(cartaDao.findById(portada).orElseThrow());
		return albumDao.save(album);
	}

	@Override
	public Carta getCarta(Integer id) {
		return cartaDao.findById(id).orElse(null);
	}

	@Override
	public List<String> getCartasAleatorias(int numero) {
		List<String> cartas = new LinkedList<String>();
		long totalCartas = cartaDao.count();

		for (int i = 0; i < numero;) {
			int indexCartaRandom = (int) Math.floor(Math.random() * totalCartas + 1);
			Carta cartaSeleccionada = cartaDao.findById(indexCartaRandom).orElse(null);
			if (cartaSeleccionada != null) {
				if (!cartas.contains(cartaSeleccionada.getScryfallId()) || totalCartas < numero) {
					cartas.add(cartaSeleccionada.getScryfallId());
					i++;
				}
			}
		}
		return cartas;
	}

	@Override
	public Carta getPortada(Integer album_id) {
		Album album = getAlbum(album_id);
		return album.getPortada();
	}

}
