package com.alolorsus.collector.service;

import org.springframework.data.domain.Page;

import com.alolorsus.collector.entity.Album;
import com.alolorsus.collector.entity.Carta;
import com.alolorsus.collector.entity.Usuario;

public interface AlbumService {
	
	public Page<Album> getAlbumsFromUser (String username, Integer pagina);
	
	public Page<Carta> getCartasFromAlbum (Integer album_id, Integer pagina);
	
	public Album crearAlbum (String nombre, Usuario usuario);
	
	public Carta agregarCarta (String externalId, Integer album_id);
	
	public void eliminarCarta (Integer carta_id);
	
	public void eliminarAlbum (Integer album_id);

}
