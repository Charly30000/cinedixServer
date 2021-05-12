
package com.cinedix.server.app.models.entity.request;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sesionPelicula",
    "sitiosOcupados"
})
@Generated("jsonschema2pojo")
public class SesionPeliculaRequest implements Serializable
{

	@NotNull
    @JsonProperty("sesionPelicula")
    private Integer sesionPelicula;
	
	@NotEmpty
    @JsonProperty("sitiosOcupados")
    private List<Integer> sitiosOcupados = null;
	
    private final static long serialVersionUID = 4331226812293877989L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SesionPeliculaRequest() {
    }

    /**
     * 
     * @param sesionPelicula
     * @param sitiosOcupados
     */
    public SesionPeliculaRequest(@NotNull Integer sesionPelicula, @NotEmpty List<Integer> sitiosOcupados) {
        super();
        this.sesionPelicula = sesionPelicula;
        this.sitiosOcupados = sitiosOcupados;
    }

    @JsonProperty("sesionPelicula")
    public Integer getSesionPelicula() {
        return sesionPelicula;
    }

    @JsonProperty("sesionPelicula")
    public void setSesionPelicula(Integer sesionPelicula) {
        this.sesionPelicula = sesionPelicula;
    }

    @JsonProperty("sitiosOcupados")
    public List<Integer> getSitiosOcupados() {
        return sitiosOcupados;
    }

    @JsonProperty("sitiosOcupados")
    public void setSitiosOcupados(List<Integer> sitiosOcupados) {
        this.sitiosOcupados = sitiosOcupados;
    }

}
