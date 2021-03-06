package com.cinedix.server.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30, unique = true, nullable = false)
	@NotEmpty
	@Size(min = 5, max = 30)
	private String username;

	@Column(length = 60, nullable = false)
	@NotEmpty
	@Size(min = 8, max = 60)
	private String password;

	@Column(nullable = false)
	private Boolean enabled;

	@Column(unique = true, nullable = false)
	@Email(regexp = "\\w+@\\w+\\.\\w+")
	@NotEmpty
	private String email;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private List<Role> roles;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "usuario")
	@JsonIgnore
	// @JsonManagedReference
	// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Entrada> entradas;

	private Usuario() {
	}

	private Usuario(Long id, @NotEmpty @Size(min = 5, max = 30) String username,
			@NotEmpty @Size(min = 8, max = 60) String password, Boolean enabled, @Email @NotEmpty String email,
			List<Role> roles, List<Entrada> entradas) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.roles = roles;
		this.entradas = entradas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	public void addEntrada(Entrada entrada) {
		this.entradas.add(entrada);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
