package com.alolorsus.mtgjson.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alolorsus.mtgjson.dao.DatosCartaDao;
import com.alolorsus.mtgjson.entity.DatosCarta;
import com.alolorsus.mtgjson.service.DatosCartaService;

@Service
public class DatosCartaServiceImp implements DatosCartaService {

	@Autowired
	private DatosCartaDao datosCartaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<String> getNombresCartas(String parteNombre) {
		return datosCartaDao.findNamesByName(parteNombre);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<DatosCarta> getDatosCarta(String nombre) {
		List<DatosCarta> cartas = datosCartaDao.findByName(nombre);
		return cartas;
	}

	@Override
	public List<DatosCarta> getCartasNombre(String nombre) {
		return null;
	}

	@Override
	public List<DatosCarta> getCartasIlustracion(String oracleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatosCarta> getCartasId(String illustrationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatosCarta getCarta(String scryfallId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
