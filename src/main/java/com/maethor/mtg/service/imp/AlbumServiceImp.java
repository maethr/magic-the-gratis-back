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

	private final int albumsPorPagina = 9;
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
	public Carta agregarCarta(String scryfallId, Integer albumId) {
		Carta carta = new Carta();
		carta.setScryfallId(scryfallId);
		Album album = albumDao.findById(albumId).orElse(null);
		if (album != null) {
			carta.setAlbum(album);
			System.out.println("casi");
		}
		return cartaDao.save(carta);
	}

	@Override
	public Album getAlbum(Integer album_id) {
		return albumDao.findById(album_id).orElse(null);
	}

	@Override
	public void eliminarCarta(Integer carta_id) {
		cartaDao.deleteById(carta_id);
	}

	@Override
	public void eliminarAlbum(Integer album_id) {
		albumDao.deleteById(album_id);
	}

	@Override
	public int countAlbumsFromUser(Usuario usuario) {
		return albumDao.countAlbumsByUsuario(usuario);
	}

	@Override
	public List<Album> getAllAlbumsFromUser(Usuario usuario) {
		return albumDao.findByUsuario(usuario);
	}

	@Override
	public Album editarAlbum(Album album, String nombre) {
		album.setNombre(nombre);
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

}
