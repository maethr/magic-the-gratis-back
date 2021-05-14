package com.alolorsus.collector.service;

import java.util.List;
import java.util.Map;

import com.alolorsus.mtgjson.entity.DatosCarta;

public interface CartaService {
	
	public List<?> getTituloCarta (String nombre);
	
	public Map<String, String> getImagenCarta (String nombreCompleto);
	
	public DatosCarta getDatosCarta (String nombreCompleto);
	
}
