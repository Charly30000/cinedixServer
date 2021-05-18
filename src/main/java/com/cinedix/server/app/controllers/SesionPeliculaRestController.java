package com.cinedix.server.app.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinedix.server.app.models.entity.SesionPelicula;
import com.cinedix.server.app.models.entity.SitioOcupado;
import com.cinedix.server.app.models.entity.response.CineResponse;
import com.cinedix.server.app.models.entity.response.SesionPeliculaResponse;
import com.cinedix.server.app.models.entity.response.SitioOcupadoResponse;
import com.cinedix.server.app.models.service.ISesionPeliculaService;

@RestController
@Secured("ROLE_USER")
@RequestMapping("/api/sesionpelicula")
public class SesionPeliculaRestController {

	@Autowired
	private ISesionPeliculaService sesionPeliculaService;

	@GetMapping("/cines/{id}")
	public List<SesionPeliculaResponse> obtenerCines(@PathVariable(value = "id") Long id) {
		List<SesionPelicula> sesionPeliculaBBDD = sesionPeliculaService.obtenerSesionesPeliculasPorPelicula(id);
		List<SesionPeliculaResponse> respuesta = new ArrayList<>();

		for (SesionPelicula p : sesionPeliculaBBDD) {
			List<SitioOcupadoResponse> sitiosOcupados = new ArrayList<>();
			for (SitioOcupado so : p.getSitiosOcupados()) {
				sitiosOcupados.add(new SitioOcupadoResponse(so.getId(), so.getSitioOcupado()));
			}

			respuesta.add(new SesionPeliculaResponse(p.getId(), p.getSitiosTotales(),
					formatearFecha(p.getHoraPelicula()), sitiosOcupados,
					new CineResponse(p.getCine().getId(), p.getCine().getNombre(), p.getCine().getLocalizacion())));
		}

		return respuesta;
	}

	@GetMapping("/{cineid}/{peliculaid}")
	public List<SesionPeliculaResponse> obtenerCinesHoras(@PathVariable(value = "cineid") Long cineId,
			@PathVariable(value = "peliculaid") Long peliculaId) {

		List<SesionPelicula> sesionPeliculaBBDD = 
				sesionPeliculaService.obtenerSesionesPeliculasPorCinePelicula(cineId, peliculaId);
		
		List<SesionPeliculaResponse> respuesta = new ArrayList<>();
		
		for (SesionPelicula sp : sesionPeliculaBBDD) {
			List<SitioOcupadoResponse> sitiosOcupados = new ArrayList<>();
			for(SitioOcupado so : sp.getSitiosOcupados()) {
				sitiosOcupados.add(new SitioOcupadoResponse(so.getId(), so.getSitioOcupado()));
			}
			
			respuesta.add(new SesionPeliculaResponse(sp.getId(), 
					sp.getSitiosTotales(), 
					formatearFecha(sp.getHoraPelicula()), 
					sitiosOcupados, 
					new CineResponse(sp.getCine().getId(), sp.getCine().getNombre(), sp.getCine().getLocalizacion())));
		}
		
		return respuesta;
	}

	private String formatearFecha(Date date) {
		String salida = date.toString();
		salida = salida.substring(0, salida.lastIndexOf(":"));

		return salida;
	}

}
