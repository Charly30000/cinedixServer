package com.cinedix.server.app.models.service;

import java.util.Date;
import java.util.List;

import com.cinedix.server.app.models.entity.SesionPelicula;

public interface ISesionPeliculaService {

	public List<SesionPelicula> findAll();
	
	public void save(SesionPelicula sesionPelicula);
	
	public SesionPelicula findOne(Long id);
	
	public void delete (Long id);
	
	public List<SesionPelicula> obtenerSesionesPeliculasPorPelicula(Long id);
	
	public SesionPelicula obtenerSesionEntradaPorCinePeliculaFecha(Long cineId, Long peliculaId, Date horaPelicula);
}
