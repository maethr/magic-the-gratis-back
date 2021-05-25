package com.alolorsus.collector.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String nombre;

	@NotNull
	private String email;

	@OneToMany(mappedBy = "usuario")
	@JsonBackReference
	private List<Album> albumes;

	// Getters y Setters
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Album> getAlbumes() {
		return albumes;
	}

	public void setAlbumes(List<Album> albumes) {
		this.albumes = albumes;
	}

	private static final long serialVersionUID = 1L;

}
