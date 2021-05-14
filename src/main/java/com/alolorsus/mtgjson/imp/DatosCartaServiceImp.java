package com.alolorsus.mtgjson.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alolorsus.collector.service.CartaService;
import com.alolorsus.mtgjson.dao.DatosCartaDao;
import com.alolorsus.mtgjson.entity.DatosCarta;
import com.alolorsus.mtgjson.service.DatosCartaService;

@Service
public class DatosCartaServiceImp implements DatosCartaService {

	@Autowired
	private DatosCartaDao datosCartaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<DatosCarta> getDatosCarta(String nombre) {
		List<DatosCarta> cartas = datosCartaDao.findByName(nombre);
		return cartas;
	}
	
}
