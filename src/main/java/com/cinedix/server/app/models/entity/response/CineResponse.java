
package com.cinedix.server.app.models.entity.response;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "nombre",
    "localizacion"
})
@Generated("jsonschema2pojo")
public class CineResponse implements Serializable
{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("localizacion")
    private String localizacion;
    private final static long serialVersionUID = -6386618666324118687L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CineResponse() {
    }

    /**
     * 
     * @param localizacion
     * @param id
     * @param nombre
     */
    public CineResponse(Long id, String nombre, String localizacion) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.localizacion = localizacion;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("localizacion")
    public String getLocalizacion() {
        return localizacion;
    }

    @JsonProperty("localizacion")
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

}
