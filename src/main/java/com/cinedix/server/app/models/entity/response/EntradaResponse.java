
package com.cinedix.server.app.models.entity.response;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "codigo", "estado", "sitiosOcupados", "horaPelicula", "cine", "pelicula", "fechaCreacion", "id" })
@Generated("jsonschema2pojo")
public class EntradaResponse implements Serializable {

	@JsonProperty("codigo")
	private String codigo;
	@JsonProperty("estado")
	private String estado;
	@JsonProperty("sitiosOcupados")
	private List<SitioOcupadoResponse> sitiosOcupados = null;
	@JsonProperty("horaPelicula")
	private String horaPelicula;
	@JsonProperty("cine")
	private String cine;
	@JsonProperty("pelicula")
	private String pelicula;
	@JsonProperty("fechaCreacion")
	private String fechaCreacion;
	@JsonProperty("id")
	private Long id;
	private final static long serialVersionUID = -3400769370976346951L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public EntradaResponse() {
	}

	/**
	 * 
	 * @param codigo
	 * @param estado
	 * @param sitiosOcupados
	 * @param horaPelicula
	 * @param cine
	 * @param pelicula
	 * @param fechaCreacion
	 * @param id
	 */
	public EntradaResponse(String codigo, String estado, List<SitioOcupadoResponse> sitiosOcupados, String horaPelicula,
			String cine, String pelicula, String fechaCreacion, Long id) {
		super();
		this.codigo = codigo;
		this.estado = estado;
		this.sitiosOcupados = sitiosOcupados;
		this.horaPelicula = horaPelicula;
		this.cine = cine;
		this.pelicula = pelicula;
		this.fechaCreacion = fechaCreacion;
		this.id = id;
	}

	@JsonProperty("codigo")
	public String getCodigo() {
		return codigo;
	}

	@JsonProperty("codigo")
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@JsonProperty("estado")
	public String getEstado() {
		return estado;
	}

	@JsonProperty("estado")
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@JsonProperty("sitiosOcupados")
	public List<SitioOcupadoResponse> getSitiosOcupados() {
		return sitiosOcupados;
	}

	@JsonProperty("sitiosOcupados")
	public void setSitiosOcupados(List<SitioOcupadoResponse> sitiosOcupados) {
		this.sitiosOcupados = sitiosOcupados;
	}

	@JsonProperty("horaPelicula")
	public String getHoraPelicula() {
		return horaPelicula;
	}

	@JsonProperty("horaPelicula")
	public void setHoraPelicula(String horaPelicula) {
		this.horaPelicula = horaPelicula;
	}

	@JsonProperty("cine")
	public String getCine() {
		return cine;
	}

	@JsonProperty("cine")
	public void setCine(String cine) {
		this.cine = cine;
	}

	@JsonProperty("pelicula")
	public String getPelicula() {
		return pelicula;
	}

	@JsonProperty("pelicula")
	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	@JsonProperty("fechaCreacion")
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	@JsonProperty("fechaCreacion")
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Long id) {
		this.id = id;
	}

}
