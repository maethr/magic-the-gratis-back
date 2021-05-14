package com.alolorsus.mtgjson.service;

import java.util.List;

import com.alolorsus.mtgjson.entity.DatosCarta;

public interface DatosCartaService {
	
	public List<DatosCarta> getDatosCarta(String nombre);

}
