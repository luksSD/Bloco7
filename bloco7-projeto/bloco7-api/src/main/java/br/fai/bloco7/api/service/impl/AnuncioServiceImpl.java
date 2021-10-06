package br.fai.bloco7.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.bloco7.api.service.AnuncioService;
import br.fai.bloco7.db.dao.AnuncioDao;
import br.fai.bloco7.model.Anuncio;

@Service
public class AnuncioServiceImpl implements AnuncioService {

	@Autowired
	private AnuncioDao dao;

	@Override
	public List<Anuncio> readAll() {

		return dao.readAll();
	}

	@Override
	public Anuncio readById(final Long id) {

		return dao.readById(id);
	}

	@Override
	public Long create(final Anuncio entity) {

		return dao.create(entity);
	}

	@Override
	public boolean update(final Anuncio entity) {

		if (entity == null)
			return false;

		return dao.update(entity);
	}

	@Override
	public boolean delete(final Long id) {

		return dao.delete(id);
	}

}
