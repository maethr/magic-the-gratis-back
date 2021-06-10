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
	public Page<MtgDBCarta> getByNombreGroupByOracle(String nombre, Integer pagina, Integer size) {
		if (pagina == null)
			pagina = 0;
		if (size == null)
			size = cartasPorBusqueda;
		Pageable pag = PageRequest.of(pagina, size);
		return dao.findByNameGroupByOracle(nombre, pag);
	}

	@Override
	public Page<MtgDBCarta> getByOracleGroupByIlustracion(String scryfallOracleId, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, cartasPorBusqueda);
		return dao.findByOracleGroupByIllustration(scryfallOracleId, pag);
	}

	@Override
	public Page<MtgDBCarta> getByIlustracionGroupById(String scryfallIllustrationId, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, cartasPorBusqueda);
		return dao.findByIllustrationGroupById(scryfallIllustrationId, pag);
	}

	@Override
	public Page<MtgDBCarta> getByNombreGroupByIlustracion(String nombre, Integer pagina, Integer size) {
		if (pagina == null)
			pagina = 0;
		if (size == null)
			size = cartasPorBusqueda;
		Pageable pag = PageRequest.of(pagina, size);
		return dao.findByNameGroupByIllustration(nombre, pag);
	}

	@Override
	public Page<MtgDBCarta> getByNombreGroupById(String nombre, Integer pagina, Integer size) {
		if (pagina == null)
			pagina = 0;
		if (size == null)
			size = cartasPorBusqueda;
		Pageable pag = PageRequest.of(pagina, size);
		return dao.findByNameGroupById(nombre, pag);
	}

	@Override
	public Page<MtgDBCarta> getByNombreGroupByOracle(String nombre, String set, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, cartasPorBusqueda);
		return dao.findByNameAndSetGroupByOracle(nombre, set, pag);
	}

	@Override
	public Page<MtgDBCarta> getByNombreGroupByIlustracion(String nombre, String set, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, cartasPorBusqueda);
		return dao.findByNameAndSetGroupByIllustration(nombre, set, pag);
	}

	@Override
	public Page<MtgDBCarta> getByNombreGroupById(String nombre, String set, Integer pagina) {
		if (pagina == null)
			pagina = 0;
		Pageable pag = PageRequest.of(pagina, cartasPorBusqueda);
		return dao.findByNameAndSetGroupById(nombre, set, pag);
	}
}
