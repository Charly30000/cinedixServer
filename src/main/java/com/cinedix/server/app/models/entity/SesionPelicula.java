package com.cinedix.server.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "sesiones_peliculas", uniqueConstraints = {@UniqueConstraint(columnNames = {"cine_id", "pelicula_id", "hora_pelicula"})})
public class SesionPelicula implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer sitiosTotales;

	@Column(nullable = false, name = "hora_pelicula")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Madrid")
	@NotNull
	private Date horaPelicula;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@NotNull
	private Cine cine;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@NotNull
	private Pelicula pelicula;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sesionPelicula")
	@JsonManagedReference
	public List<SitioOcupado> sitiosOcupados;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidadSitiosDisponibles() {
		return sitiosTotales;
	}

	public void setCantidadSitiosDisponibles(Integer sitiosTotales) {
		this.sitiosTotales = sitiosTotales;
	}

	public Date getHoraPelicula() {
		return horaPelicula;
	}

	public void setHoraPelicula(Date horaPelicula) {
		this.horaPelicula = horaPelicula;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Integer getSitiosTotales() {
		return sitiosTotales;
	}

	public void setSitiosTotales(Integer sitiosTotales) {
		this.sitiosTotales = sitiosTotales;
	}

	public List<SitioOcupado> getSitiosOcupados() {
		return sitiosOcupados;
	}

	public void setSitiosOcupados(List<SitioOcupado> sitiosOcupados) {
		this.sitiosOcupados = sitiosOcupados;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
