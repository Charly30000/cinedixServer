package com.cinedix.server.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinedix.server.app.models.entity.Pelicula;
import com.cinedix.server.app.models.service.IPeliculasService;

@RestController
@Secured("ROLE_USER")
@RequestMapping("/api/clientes")
public class PeliculasRestController {

	@Autowired
	private IPeliculasService peliculaService;
	
	@GetMapping("/peliculas/venta")
	public List<Pelicula> obtenerPeliculasVenta() {
		return peliculaService.obtenerPeliculasEnVenta();
	}
	
	@GetMapping("/peliculas/estrenos")
	public List<Pelicula> obtenerPeliculasEstreno() {
		return peliculaService.obtenerPeliculasEnEstreno();
	}
	
	@GetMapping("/peliculas/{id}")
	public Pelicula obtenerPelicula(@PathVariable(value = "id") Long id) {
		return peliculaService.findOne(id);
	}
}
