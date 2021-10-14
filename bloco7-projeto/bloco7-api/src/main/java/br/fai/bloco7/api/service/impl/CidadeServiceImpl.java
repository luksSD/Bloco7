package br.fai.bloco7.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.bloco7.api.service.CidadeService;
import br.fai.bloco7.db.dao.CidadeDao;
import br.fai.bloco7.model.Cidade;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeDao dao;

	@Override
	public Cidade readById(final Long id) {

		return dao.readById(id);
	}

}
