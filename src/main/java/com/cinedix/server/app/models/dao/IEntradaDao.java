package com.cinedix.server.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cinedix.server.app.models.entity.Entrada;

public interface IEntradaDao extends CrudRepository<Entrada, Long>{

	@Query("SELECT e FROM Entrada e WHERE e.usuario.id = ?1")
	public List<Entrada> buscarPorUsuario(Long id);
	
	//@Query("SELECT e FROM Entrada e WHERE e.sitiosOcupados.sesionPelicula.pelicula.id = ?1")
	//public List<Entrada> obtenerEntradasPorPelicula(Long id);
}
