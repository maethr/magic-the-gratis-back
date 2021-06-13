package com.alolorsus.collector.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alolorsus.collector.entity.Album;
import com.alolorsus.collector.entity.Carta;
import com.alolorsus.collector.entity.Usuario;

public interface AlbumService {
	
	// Albums
	
	public List<Album> getAllAlbumsFromUser (Usuario usuario);
	
	public Page<Album> getAlbumsFromUser (Usuario usuario, Integer pagina);
	
	public Album getAlbum (Integer album_id);
	
	public Album crearAlbum (String nombre, Usuario usuario);
	
	public Album editarAlbum (Album album, String nombre);
	
	public void eliminarAlbum (Integer album_id);
	
	public int countAlbumsFromUser (Usuario usuario);
	
	// Cartas
	
	public Carta getCarta (Integer id);
	
	public Page<Carta> getCartasFromAlbum (Album album, Integer pagina, Integer size);
	
	public Carta agregarCarta (String externalId, Integer album_id);
	
	public void eliminarCarta (Integer carta_id);

}
