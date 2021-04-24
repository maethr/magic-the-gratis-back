package com.alolorsus.collector.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "albums")
public class Album implements Serializable {

	@Id
	private Integer id;
	
	@NotEmpty
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	private Usuario usuario;

	@NotEmpty
	private String nombre;

	private String juego;
	
	@OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Carta> cartas;
	
	// Getters y Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getJuego() {
		return juego;
	}

	public void setJuego(String juego) {
		this.juego = juego;
	}

	private static final long serialVersionUID = 1L;

}
