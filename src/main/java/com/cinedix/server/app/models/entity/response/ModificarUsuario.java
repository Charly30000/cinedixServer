
package com.cinedix.server.app.models.entity.response;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "username",
    "newPassword",
    "oldPassword",
    "email"
})
@Generated("jsonschema2pojo")
public class ModificarUsuario implements Serializable
{

    @JsonProperty("username")
    private String username;
    @JsonProperty("newPassword")
    private String newPassword;
    @JsonProperty("oldPassword")
    private String oldPassword;
    @JsonProperty("email")
    private String email;
    private final static long serialVersionUID = -1390128381048080161L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModificarUsuario() {
    }

    /**
     * 
     * @param oldPassword
     * @param newPassword
     * @param email
     * @param username
     */
    public ModificarUsuario(String username, String newPassword, String oldPassword, String email) {
        super();
        this.username = username;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.email = email;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("newPassword")
    public String getNewPassword() {
        return newPassword;
    }

    @JsonProperty("newPassword")
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @JsonProperty("oldPassword")
    public String getOldPassword() {
        return oldPassword;
    }

    @JsonProperty("oldPassword")
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

}
