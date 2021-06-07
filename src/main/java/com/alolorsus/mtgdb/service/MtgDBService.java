package com.alolorsus.mtgdb.service;

import org.springframework.data.domain.Page;

import com.alolorsus.mtgdb.entity.MtgDBCarta;

public interface MtgDBService {
	
	public MtgDBCarta getCarta (String scryfallId);
	
	///// Buscador
	
	public Page<MtgDBCarta> getByNombreGroupByOracle (String nombre, Integer pagina);
	
	public Page<MtgDBCarta> getByNombreGroupByIlustracion (String nombre, Integer pagina);
	
	public Page<MtgDBCarta> getByNombreGroupById (String nombre, Integer pagina);
	
	public Page<MtgDBCarta> getByOracleGroupByIlustracion (String scryfallOracleId, Integer pagina);
	
	public Page<MtgDBCarta> getByIlustracionGroupById (String scryfallIllustrationId, Integer pagina);
	
	///// Buscador set
	
	public Page<MtgDBCarta> getByNombreGroupByOracle (String nombre, String set, Integer pagina);
	
	public Page<MtgDBCarta> getByNombreGroupByIlustracion (String nombre, String set, Integer pagina);
	
	public Page<MtgDBCarta> getByNombreGroupById (String nombre,String set, Integer pagina);
}
