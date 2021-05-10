package com.cinedix.server.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinedix.server.app.models.dao.ICineDao;
import com.cinedix.server.app.models.entity.Cine;

@Service
public class CineServiceImpl implements ICineService {

	@Autowired
	private ICineDao cineDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cine> findAll() {
		return (List<Cine>) cineDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cine findOne(Long id) {
		return cineDao.findById(id).orElse(null);
	}

}
