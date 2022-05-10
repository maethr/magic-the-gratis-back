package com.maethor.mtg.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.maethor.mtg.entity.Album;
import com.maethor.mtg.entity.Usuario;

public interface AlbumService {
	
	public List<Album> getAllAlbumsFromUser (Usuario usuario);
	
	public Page<Album> getAlbumsFromUser (Usuario usuario, Integer pagina);
	
	public Album getAlbum (Integer album_id);
	
	public Album crearAlbum (String nombre, Usuario usuario);
	
	public Album editarAlbum (Integer id, String nombre, Integer portada, String colores);
	
	public void eliminarAlbum (Album album);
	
	public int countAlbumsFromUser (Usuario usuario);
	
	public int countCartasAlbum (int id);

}
