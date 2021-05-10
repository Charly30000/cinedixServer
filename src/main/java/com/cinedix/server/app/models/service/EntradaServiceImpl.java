package com.cinedix.server.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinedix.server.app.models.dao.IEntradaDao;
import com.cinedix.server.app.models.entity.Entrada;

@Service
public class EntradaServiceImpl implements IEntradaService {

	@Autowired
	private IEntradaDao entradaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Entrada> findAll() {
		return (List<Entrada>) entradaDao.findAll();
	}

	@Override
	@Transactional
	public void save(Entrada entrada) {
		entradaDao.save(entrada);
	}

	@Override
	@Transactional(readOnly = true)
	public Entrada findOne(Long id) {
		return entradaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		entradaDao.deleteById(id);;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entrada> buscarPorUsuario(Long id) {
		return entradaDao.buscarPorUsuario(id);
	}

}
