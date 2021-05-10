package com.cinedix.server.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cinedix.server.app.models.entity.SitioOcupado;

public interface ISitioOcupadoDao extends CrudRepository<SitioOcupado, Long> {

	@Query("SELECT count(so) FROM SitioOcupado so WHERE so.sesionPelicula = ?1")
	public Integer cantidadSitiosOcupados(Long id);
	
	@Query("SELECT so FROM SitioOcupado so WHERE so.sesionPelicula.id = ?1")
	public List<SitioOcupado> obtenerSitiosOcupadosPorSesionPeliculas(Long id);
}
