package com.maethor.mtg.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.maethor.mtg.entity.Album;
import com.maethor.mtg.entity.Carta;
import com.maethor.mtg.entity.Usuario;

public interface AlbumService {
	
	// Albums
	
	public List<Album> getAllAlbumsFromUser (Usuario usuario);
	
	public Page<Album> getAlbumsFromUser (Usuario usuario, Integer pagina);
	
	public Album getAlbum (Integer album_id);
	
	public Album crearAlbum (String nombre, Usuario usuario);
	
	public Album editarAlbum (Integer id, String nombre, Integer portada);
	
	public void eliminarAlbum (Album album);
	
	public int countAlbumsFromUser (Usuario usuario);
	
	public int countCartasAlbum (int id);
	
	// Cartas
	
	public Carta getPortada (Integer id);
	
	public Carta getCarta (Integer id);
	
	public List<Carta> getAllCartasFromAlbum (Album album);
	
	public Page<Carta> getCartasFromAlbum (Album album, Integer pagina, Integer size);
	
	public Carta agregarCarta (String externalId, Integer albumId, Integer amount);
	
	public void eliminarCarta (Integer carta_id);
	
	public List<String> getCartasAleatorias (int numero); 

}
