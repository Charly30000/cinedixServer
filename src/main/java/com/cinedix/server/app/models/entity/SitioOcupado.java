package com.cinedix.server.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "sitios_ocupados", uniqueConstraints = {@UniqueConstraint(columnNames = {"sitio_ocupado", "sesion_pelicula_id"})})
public class SitioOcupado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable = false, name = "sitio_ocupado")
	private Integer sitioOcupado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private SesionPelicula sesionPelicula;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Entrada entrada;

	public SesionPelicula getSesionPelicula() {
		return sesionPelicula;
	}

	public void setSesionPelicula(SesionPelicula sesionPelicula) {
		this.sesionPelicula = sesionPelicula;
	}

	public Integer getSitioOcupado() {
		return sitioOcupado;
	}

	public void setSitioOcupado(Integer sitioOcupado) {
		this.sitioOcupado = sitioOcupado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
