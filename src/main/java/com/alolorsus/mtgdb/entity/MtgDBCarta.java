package com.alolorsus.mtgdb.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import com.alolorsus.mtgdb.service.test.ScryfallService;


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
	
	
	// TEST //////////////////////////////////
	@Transient
	private Map<String, String> urls;
	
	@PostLoad
	public void setUrls() {
		setUrls(ScryfallService.getImagenesCarta(scryfallId));
	}
	
	public Map<String, String> getUrls() {
		return urls;
	}
	
	public void setUrls(Map<String, String> urls) {
		this.urls = urls;
	}
	//////////////////////////////////////////

	
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

	private static final long serialVersionUID = 1L;

}
