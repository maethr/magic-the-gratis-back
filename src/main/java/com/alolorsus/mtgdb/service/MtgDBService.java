package com.alolorsus.mtgdb.service;

import org.springframework.data.domain.Page;

import com.alolorsus.mtgdb.entity.MtgDBCarta;

public interface MtgDBService {
	
	public Page<MtgDBCarta> getByNombreGroupByOracle (String nombre, Integer pagina);
	
	public Page<MtgDBCarta> getByNombreGroupByIlustracion (String nombre, Integer pagina);
	
	public Page<MtgDBCarta> getByNombreGroupById (String nombre, Integer pagina);
	
	public Page<MtgDBCarta> getByOracleGroupByIlustracion (String scryfallOracleId, Integer pagina);
	
	public Page<MtgDBCarta> getByIlustracionGroupById (String scryfallIllustrationId, Integer pagina);
	
	public MtgDBCarta getCarta (String scryfallId);

}
