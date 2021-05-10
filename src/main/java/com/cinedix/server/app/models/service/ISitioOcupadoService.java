package com.cinedix.server.app.models.service;

import java.util.List;

import com.cinedix.server.app.models.entity.SitioOcupado;

public interface ISitioOcupadoService {

	public List<SitioOcupado> findAll();
	
	public void save(SitioOcupado sitioOcupado);
	
	public SitioOcupado findOne(Long id);
	
	public void delete (Long id);
	
	public Integer cantidadSitiosOcupados(Long id);
	
	public List<SitioOcupado> obtenerSitiosOcupadosPorSesionPeliculas(Long id);
}
