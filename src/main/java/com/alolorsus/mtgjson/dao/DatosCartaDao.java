package com.alolorsus.mtgjson.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alolorsus.mtgjson.entity.DatosCarta;

public interface DatosCartaDao extends JpaRepository<DatosCarta, String> {
	
	@Query("select name from cards c where c.name like %?1% group by scryfallOracleId")
	public List<String> findNamesByName (String part);
	
	@Query("select c from cards c where c.name like ?1 group by scryfallOracleId")
	public List<DatosCarta> findByNameGroupByOracle (String name);
	
	@Query("select c from cards c where c.name like ?1 group by scryfallIllustrationId")
	public List<DatosCarta> findByNameGroupByIllustration (String name);
	
	@Query("select c from cards c where c.name like ?1 group by scryfallId")
	public List<DatosCarta> findByName(String name);
	
	@Query("select c from cards c where c.scryfallOracleId like ?1 group by scryfallIllustrationId")
	public List<DatosCarta> findByOracleGroupByIllustration (String oracleId);
	
	@Query("select c from cards c where c.scryfallIllustrationId like ?1 group by scryfallId")
	public List<DatosCarta> findByIllustration(String illustrationId);
}
