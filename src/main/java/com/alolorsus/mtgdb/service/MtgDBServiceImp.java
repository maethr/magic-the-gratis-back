package com.alolorsus.mtgdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alolorsus.mtgdb.entity.MtgDBCarta;

@Service
public class MtgDBServiceImp implements MtgDBService {

	private final int cartasPorBusqueda = 9;

	@Autowired
	private MtgDBDao dao;

	@Override
	public MtgDBCarta getCarta(String externalId) {
		return dao.findByScryfallId(externalId);
	}

	@Override
	public Page<MtgDBCarta> getCartasGroupByOracle(String nombre, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, cartasPorBusqueda);
		return dao.findByNombre(nombre, pag);
	}

	@Override
	public Page<MtgDBCarta> getCartasGroupByIlustracion(String scryfallOracleId, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, cartasPorBusqueda);
		return dao.findByScryfallOracleId(scryfallOracleId, pag);
	}

	@Override
	public Page<MtgDBCarta> getCartas(String scryfallIllustrationId, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, cartasPorBusqueda);
		return dao.findByScryfallIllustrationId(scryfallIllustrationId, pag);
	}

}
