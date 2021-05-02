package com.cinedix.server.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sesiones_peliculas")
public class SesionPelicula implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private Integer cantidadSitiosDisponibles;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date horaPelicula;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cine cine;

	@ManyToOne(fetch = FetchType.LAZY)
	private Pelicula pelicula;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sesionPelicula")
	private List<SitioOcupado> sitiosOcupados;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidadSitiosDisponibles() {
		return cantidadSitiosDisponibles;
	}

	public void setCantidadSitiosDisponibles(Integer cantidadSitiosDisponibles) {
		this.cantidadSitiosDisponibles = cantidadSitiosDisponibles;
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

	public List<SitioOcupado> getSitiosOcupados() {
		return sitiosOcupados;
	}

	public void setSitiosOcupados(List<SitioOcupado> sitiosOcupados) {
		this.sitiosOcupados = sitiosOcupados;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public void addSitiosOcupados(SitioOcupado sitioOcupado) {
		this.sitiosOcupados.add(sitioOcupado);
		this.setCantidadSitiosDisponibles(this.getCantidadSitiosDisponibles() - 1);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
