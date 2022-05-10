package com.maethor.mtg.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.maethor.mtg.entity.Album;
import com.maethor.mtg.entity.Carta;

public interface CartasService {
	
		public Carta getPortada (Integer id);
		
		public Carta getCarta (Integer id);
		
		public List<Carta> getAllCartasFromAlbum (Album album);
		
		public Page<Carta> getCartasFromAlbum (Album album, Integer pagina, Integer size);
		
		public Carta agregarCarta (String externalId, Integer albumId);
		
		public void eliminarCarta (Integer carta_id);
		
		public List<String> getCartasAleatorias (int numero); 

}
