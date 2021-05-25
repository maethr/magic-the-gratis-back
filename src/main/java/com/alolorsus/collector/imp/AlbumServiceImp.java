package com.alolorsus.collector.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alolorsus.collector.dao.AlbumDao;
import com.alolorsus.collector.dao.CartaDao;
import com.alolorsus.collector.entity.Album;
import com.alolorsus.collector.entity.Carta;
import com.alolorsus.collector.entity.Juego;
import com.alolorsus.collector.entity.Usuario;
import com.alolorsus.collector.service.AlbumService;

@Service
public class AlbumServiceImp implements AlbumService {

	@Autowired
	private AlbumDao albumDao;

	@Autowired
	private CartaDao cartaDao;

	private Integer albumsPorPagina = 10;
	
	private Integer cartasPorPagina = 9;

	@Override
	public Page<Album> getAlbumsFromUser(String usuario, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, albumsPorPagina);
		return albumDao.findByUsuario(usuario, pag);
	}

	@Override
	public Page<Carta> getCartasFromAlbum(Integer album_id, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, cartasPorPagina);
		return cartaDao.findByAlbum(album_id, pag);
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
	public Carta agregarCarta(String externalId, Integer album_id) {
		Carta carta = new Carta();
		carta.setExternalId(externalId);
		Album album = albumDao.findById(album_id).orElse(null);
		if (album != null) {
			carta.setAlbum(album);
			
		} // else error
		return cartaDao.save(carta);
	}

	@Override
	public void eliminarCarta(Integer carta_id) {
		cartaDao.deleteById(carta_id);
	}

	@Override
	public void eliminarAlbum(Integer album_id) {
		albumDao.deleteById(album_id);
	}


}
