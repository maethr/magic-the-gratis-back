package com.alolorsus.mtgdb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alolorsus.mtgdb.entity.MtgDBCarta;

public interface MtgDBDao extends CrudRepository<MtgDBCarta, Integer>{

	@Query("select c from cards c where c.scryfallId like ?1 group by scryfallId")
	public MtgDBCarta findByScryfallId(String scryfallId);
	
	@Query("select c from cards c where c.name like %?1% group by scryfallOracleId")
	public Page<MtgDBCarta> findByNameGroupByOracle (String nombre, Pageable pagina);
	
	@Query("select c from cards c where c.name like %?1% group by scryfallIllustrationId")
	public Page<MtgDBCarta> findByNameGroupByIllustration (String nombre, Pageable pagina);
	
	@Query("select c from cards c where c.name like %?1% group by scryfallId")
	public Page<MtgDBCarta> findByNameGroupById (String nombre, Pageable pagina);
	
	@Query("select c from cards c where c.scryfallOracleId like ?1 group by scryfallIllustrationId")
	public Page<MtgDBCarta> findByOracleGroupByIllustration (String scryfallOracleId, Pageable pagina);
	
	@Query("select c from cards c where c.scryfallIllustrationId like ?1 group by scryfallId")
	public Page<MtgDBCarta> findByIllustrationGroupById (String scryfallIllustrationId, Pageable pagina);
	
}
