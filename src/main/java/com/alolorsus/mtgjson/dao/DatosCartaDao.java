package com.alolorsus.mtgjson.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alolorsus.mtgjson.entity.DatosCarta;

public interface DatosCartaDao extends JpaRepository<DatosCarta, String> {
	
	@Query("select c from cards c where c.name like ?1")
	public List<DatosCarta> findByName(String name);
	
}
