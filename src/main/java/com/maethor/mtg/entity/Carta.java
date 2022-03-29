package com.maethor.mtg.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "cartas")
public class Carta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name = "scryfallId")
	private String scryfall_id;

	@ManyToOne
	@JsonManagedReference
	@JsonIdentityReference(alwaysAsId = true)
	private Album album;

	private Integer amount;

	// Getters y Setters

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		if (amount == null) {
			return 1;
		}
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@JsonProperty(value = "scryfall_id")
	public String getScryfallId() {
		return scryfall_id;
	}

	public void setScryfallId(String scryfallId) {
		this.scryfall_id = scryfallId;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
}
