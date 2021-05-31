package com.alolorsus.mtgdb.service;

import org.springframework.data.domain.Page;

import com.alolorsus.mtgdb.entity.MtgDBCarta;

public interface MtgDBService {
	
	public Page<MtgDBCarta> getCartasGroupByOracle (String nombre, Integer pagina);
	
	public Page<MtgDBCarta> getCartasGroupByIlustracion (String scryfallOracleId, Integer pagina);
	
	public Page<MtgDBCarta> getCartas (String scryfallIllustrationId, Integer pagina);
	
	public MtgDBCarta getCarta (String scryfallId);

}
