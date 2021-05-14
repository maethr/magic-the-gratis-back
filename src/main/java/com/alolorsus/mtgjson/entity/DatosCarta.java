package com.alolorsus.mtgjson.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import com.alolorsus.collector.util.Rareza;
import com.alolorsus.mtgjson.service.ScryfallService;

import lombok.Data;

@Entity(name = "cards")
@Data
public class DatosCarta implements Serializable {

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
	
	@Transient
	private Map<String, String> imageUrls;
	
	@PostLoad
	private void getImagenes () {
		Map<String, String> imageUrls = ScryfallService.getImagenesCarta(scryfallId);
		setImageUrls(imageUrls);
	}
	
	private static final long serialVersionUID = 1L;

}
