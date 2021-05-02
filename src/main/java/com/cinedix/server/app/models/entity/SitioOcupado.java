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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sitios_ocupados")
public class SitioOcupado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private SesionPelicula sesionPelicula;

	@NotNull
	@Column(nullable = false)
	private Integer sitioOcupado;
	
	@ManyToOne(fetch = FetchType.LAZY)
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
