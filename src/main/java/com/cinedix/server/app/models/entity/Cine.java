package com.cinedix.server.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "cines")
public class Cine implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false)
	private String nombre;

	@NotEmpty
	@Column(nullable = false)
	private String localizacion;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Pelicula> peliculas;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "cine")
	private List<SesionPelicula> sesionesPelicula;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public List<SesionPelicula> getSesionesPelicula() {
		return sesionesPelicula;
	}

	public void setSesionesPelicula(List<SesionPelicula> sesionesPelicula) {
		this.sesionesPelicula = sesionesPelicula;
	}
	
	public void addPelicula(Pelicula pelicula) {
		this.peliculas.add(pelicula);
	}
	
	public void addSesionPelicula(SesionPelicula sesionPelicula) {
		this.sesionesPelicula.add(sesionPelicula);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
