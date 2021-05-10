package com.cinedix.server.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cinedix.server.app.models.entity.Pelicula;

public interface IPeliculasDao extends CrudRepository<Pelicula, Long>{

	@Query("SELECT p FROM Pelicula p WHERE p.estreno = false")
	public List<Pelicula> obtenerPeliculasEnVenta();
	
	@Query("SELECT p FROM Pelicula p WHERE p.estreno = true")
	public List<Pelicula> obtenerPeliculasEnEstreno();
}
