package com.cinedix.server.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinedix.server.app.models.dao.ISitioOcupadoDao;
import com.cinedix.server.app.models.entity.SitioOcupado;

@Service
public class SitioOcupadoServiceImpl implements ISitioOcupadoService {

	@Autowired
	private ISitioOcupadoDao sitioOcupadoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<SitioOcupado> findAll() {
		return (List<SitioOcupado>) sitioOcupadoDao.findAll();
	}

	@Override
	@Transactional
	public void save(SitioOcupado sitioOcupado) {
		sitioOcupadoDao.save(sitioOcupado);
	}

	@Override
	@Transactional(readOnly = true)
	public SitioOcupado findOne(Long id) {
		return sitioOcupadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		sitioOcupadoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer cantidadSitiosOcupados(Long id) {
		return sitioOcupadoDao.cantidadSitiosOcupados(id);
	}

	@Override
	public List<SitioOcupado> obtenerSitiosOcupadosPorSesionPeliculas(Long id) {
		return sitioOcupadoDao.obtenerSitiosOcupadosPorSesionPeliculas(id);
	}

}
