
package com.cinedix.server.app.models.entity.response;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "sitiosTotales",
    "horaPelicula",
    "sitiosOcupados",
    "cantidadSitiosDisponibles",
    "cine"
})
@Generated("jsonschema2pojo")
public class SesionPeliculaResponse implements Serializable
{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("sitiosTotales")
    private Integer sitiosTotales;
    @JsonProperty("horaPelicula")
    private String horaPelicula;
    @JsonProperty("sitiosOcupados")
    private List<SitioOcupadoResponse> sitiosOcupados = null;
    @JsonProperty("cine")
    private CineResponse cine;
    private final static long serialVersionUID = 4168081462243381261L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SesionPeliculaResponse() {
    }

    /**
     * 
     * @param sitiosTotales
     * @param horaPelicula
     * @param sitiosOcupados
     * @param cine
     * @param id
     * @param cantidadSitiosDisponibles
     */
    public SesionPeliculaResponse(Long id, Integer sitiosTotales, String horaPelicula, List<SitioOcupadoResponse> sitiosOcupados, CineResponse cine) {
        super();
        this.id = id;
        this.sitiosTotales = sitiosTotales;
        this.horaPelicula = horaPelicula;
        this.sitiosOcupados = sitiosOcupados;
        this.cine = cine;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("sitiosTotales")
    public Integer getSitiosTotales() {
        return sitiosTotales;
    }

    @JsonProperty("sitiosTotales")
    public void setSitiosTotales(Integer sitiosTotales) {
        this.sitiosTotales = sitiosTotales;
    }

    @JsonProperty("horaPelicula")
    public String getHoraPelicula() {
        return horaPelicula;
    }

    @JsonProperty("horaPelicula")
    public void setHoraPelicula(String horaPelicula) {
        this.horaPelicula = horaPelicula;
    }

    @JsonProperty("sitiosOcupados")
    public List<SitioOcupadoResponse> getSitiosOcupados() {
        return sitiosOcupados;
    }

    @JsonProperty("sitiosOcupados")
    public void setSitiosOcupados(List<SitioOcupadoResponse> sitiosOcupados) {
        this.sitiosOcupados = sitiosOcupados;
    }

    @JsonProperty("cine")
    public CineResponse getCine() {
        return cine;
    }

    @JsonProperty("cine")
    public void setCine(CineResponse cine) {
        this.cine = cine;
    }

}
