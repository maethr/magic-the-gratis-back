package com.alolorsus.collector.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	private Integer id;

	@NotEmpty
	private String pass;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String email;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	@JsonBackReference // No lo incluye en la bbdd, ni en los Json.
	private List<Album> albums;

	// Getters y Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	private static final long serialVersionUID = 1L;

}
