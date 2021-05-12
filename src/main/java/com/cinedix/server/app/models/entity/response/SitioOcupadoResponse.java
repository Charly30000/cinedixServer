
package com.cinedix.server.app.models.entity.response;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "sitioOcupado"
})
@Generated("jsonschema2pojo")
public class SitioOcupadoResponse implements Serializable
{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("sitioOcupado")
    private Integer sitioOcupado;
    private final static long serialVersionUID = -5516959670974940305L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SitioOcupadoResponse() {
    }

    /**
     * 
     * @param id
     * @param sitioOcupado
     */
    public SitioOcupadoResponse(Long id, Integer sitioOcupado) {
        super();
        this.id = id;
        this.sitioOcupado = sitioOcupado;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("sitioOcupado")
    public Integer getSitioOcupado() {
        return sitioOcupado;
    }

    @JsonProperty("sitioOcupado")
    public void setSitioOcupado(Integer sitioOcupado) {
        this.sitioOcupado = sitioOcupado;
    }

}
