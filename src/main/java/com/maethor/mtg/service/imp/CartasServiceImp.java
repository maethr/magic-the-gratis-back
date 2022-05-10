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
import com.maethor.mtg.service.CartasService;

@Service
public class CartasServiceImp implements CartasService {
	 
	private final int cartasPorPagina = 9;

	@Autowired
	private AlbumDao albumDao;
	
	@Autowired
	private CartaDao cartaDao;

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
	
	public Album getAlbum(Integer album_id) {
		return albumDao.findById(album_id).orElseThrow();
	}


	@Override
	public Carta getPortada(Integer album_id) {
		Album album = getAlbum(album_id);
		return album.getPortada();
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
	public Carta agregarCarta(String scryfallId, Integer albumId) {
		Carta carta = new Carta();
		carta.setScryfallId(scryfallId);
		
		Album album = albumDao.findById(albumId).orElseThrow();
		if (album != null) {
			carta.setAlbum(album);
		}
		return cartaDao.save(carta);
	}
	
	@Override
	public void eliminarCarta(Integer carta_id) {
		cartaDao.deleteById(carta_id);
	}


}
