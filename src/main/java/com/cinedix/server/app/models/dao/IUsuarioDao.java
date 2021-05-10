package com.cinedix.server.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cinedix.server.app.models.entity.Usuario;

public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long>{

	//@Query("select u from Usuario where u.username = ?1")
	public Usuario findByUsername(String username);
	
	public Usuario findById(Integer id);
}
