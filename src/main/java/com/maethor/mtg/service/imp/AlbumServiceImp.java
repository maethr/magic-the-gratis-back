package com.maethor.mtg.service.imp;

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
	public Album crearAlbum(String nombre, Usuario usuario) {
		Album album = new Album();
		album.setNombre(nombre);
		album.setUsuario(usuario);
		album.setJuego(Juego.Magic);
		return albumDao.save(album);
	}

	@Override
	public Album getAlbum(Integer album_id) {
		return albumDao.findById(album_id).orElseThrow();
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
		Album album = albumDao.findById(id).orElseThrow();
		List<Carta> cartasAlbum = cartaDao.findAllByAlbum(album);
		return cartasAlbum.size();
				
	}

	@Override
	public List<Album> getAllAlbumsFromUser(Usuario usuario) {
		return albumDao.findByUsuario(usuario);
	}

	@Override
	public Album editarAlbum(Integer id, String nombre, Integer portada_id, String colores) {
		Album album = albumDao.findById(id).orElseThrow();
		album.setNombre(nombre);
		Carta portada;
		if (portada_id == null) {
			portada = null;
		} else {
			portada = cartaDao.findById(portada_id).orElse(null);
		}
		album.setPortada(portada);
		album.setColores(colores);
		return albumDao.save(album);
	}
}
