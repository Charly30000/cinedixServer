package com.cinedix.server.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.cinedix.server.app.models.entity.Cine;

public interface ICineDao extends CrudRepository<Cine, Long> {

}
