package com.alolorsus.mtgdb.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;


@Entity(name = "cards")
public class MtgDBCarta implements Serializable {

	@Id
	private Integer id;
	
	private String name;
	
	private String manaCost;
	
	private Float convertedManaCost;
	
	private String colors;
	
	@Enumerated(EnumType.STRING)
	private Rareza rarity;
	
	private String scryfallId;
	private String scryfallIllustrationId;
	private String scryfallOracleId;
	
	private String setCode;

	
	// Getters

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getManaCost() {
		return manaCost;
	}

	public Float getConvertedManaCost() {
		return convertedManaCost;
	}

	public String getColors() {
		return colors;
	}

	public Rareza getRarity() {
		return rarity;
	}

	public String getScryfallId() {
		return scryfallId;
	}

	public String getScryfallIllustrationId() {
		return scryfallIllustrationId;
	}

	public String getScryfallOracleId() {
		return scryfallOracleId;
	}

	public String getSetCode() {
		return setCode;
	}

	public void setSetCode(String setCode) {
		this.setCode = setCode;
	}

	private static final long serialVersionUID = 1L;

}
