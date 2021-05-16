package com.alolorsus.mtgjson.service;

import java.util.List;
import com.alolorsus.mtgjson.entity.DatosCarta;

public interface DatosCartaService {
	
	// get lista nombres by partenombre group by oracle
	public List<String> getNombresCartas(String parteNombre);
	
	// get lista cartas by partenombre group by oracle
	public List<DatosCarta> getCartasNombre (String nombre);
	
	// get lista cartas by oracle group by ilustracion
	public List<DatosCarta> getCartasIlustracion (String oracleId);
	
	// get lista cartas by ilustracion group by id
	public List<DatosCarta> getCartasId (String illustrationId);
	
	// get datos by id
	public DatosCarta getCarta (String scryfallId);
	
	// 
	public List<DatosCarta> getDatosCarta(String scryfallId);

}
