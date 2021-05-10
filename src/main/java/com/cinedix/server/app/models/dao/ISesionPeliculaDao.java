package com.cinedix.server.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cinedix.server.app.models.entity.SesionPelicula;

public interface ISesionPeliculaDao extends CrudRepository<SesionPelicula, Long> {

	@Query("SELECT sp FROM SesionPelicula sp WHERE sp.pelicula.id = ?1")
	public List<SesionPelicula> obtenerSesionesPeliculasPorPelicula(Long id);
	
	@Query("SELECT sp FROM SesionPelicula sp WHERE sp.cine.id = ?1 AND sp.pelicula.id = ?2 AND sp.horaPelicula = ?3")
	public SesionPelicula obtenerSesionEntradaPorCinePeliculaFecha(Long cineId, Long peliculaId, Date horaPelicula);
}
