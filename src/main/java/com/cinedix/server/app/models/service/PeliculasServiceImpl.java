package com.cinedix.server.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinedix.server.app.models.dao.IPeliculasDao;
import com.cinedix.server.app.models.entity.Pelicula;
import com.cinedix.server.app.models.entity.SesionPelicula;
import com.cinedix.server.app.models.entity.SitioOcupado;

@Service
public class PeliculasServiceImpl implements IPeliculasService {

	@Autowired
	private IPeliculasDao peliculasDao;

	@Autowired
	private ISitioOcupadoService sitioOcupadoService;

	@Autowired
	private IEntradaService entradaService;

	@Autowired
	private ISesionPeliculaService sesionPeliculaService;

	@Override
	@Transactional(readOnly = true)
	public List<Pelicula> findAll() {
		// TODO Auto-generated method stub
		return (List<Pelicula>) peliculasDao.findAll();
	}

	@Override
	@Transactional
	public void save(Pelicula pelicula) {
		peliculasDao.save(pelicula);

	}

	@Override
	@Transactional(readOnly = true)
	public Pelicula findOne(Long id) {
		// TODO Auto-generated method stub
		return peliculasDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		List<SesionPelicula> sesionesPeliculas = sesionPeliculaService.obtenerSesionesPeliculasPorPelicula(id);
		for (SesionPelicula sp : sesionesPeliculas) {
			Long idSesiones = sp.getId();

			List<SitioOcupado> sitiosOcupados = sitioOcupadoService.obtenerSitiosOcupadosPorSesionPeliculas(idSesiones);
			for (SitioOcupado so : sitiosOcupados) {
				Long idSitioOcupado = so.getId();
				Long idEntrada = so.getEntrada().getId();
				sitioOcupadoService.delete(idSitioOcupado);
				entradaService.delete(idEntrada);
			}
			sesionPeliculaService.delete(idSesiones);
		}
		peliculasDao.deleteById(id);

	}

	@Override
	public List<Pelicula> obtenerPeliculasEnVenta() {
		return peliculasDao.obtenerPeliculasEnVenta();
	}

	@Override
	public List<Pelicula> obtenerPeliculasEnEstreno() {
		return peliculasDao.obtenerPeliculasEnEstreno();
	}

}
