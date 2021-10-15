package br.fai.bloco7.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.bloco7.api.service.PessoaService;
import br.fai.bloco7.db.dao.PessoaDao;
import br.fai.bloco7.model.Pessoa;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaDao dao;

	@Override
	public List<Pessoa> readAll() {

		return dao.readAll();
	}

	@Override
	public Pessoa readById(final Long id) {

		return dao.readById(id);
	}

	@Override
	public Long create(final Pessoa entity) {

		return dao.create(entity);
	}

	@Override
	public boolean update(final Pessoa entity) {

		if (entity == null)
			return false;

		return dao.update(entity);
	}

	@Override
	public boolean delete(final Long id) {

		return dao.delete(id);
	}
	
	@Override
	public Pessoa authentication(Pessoa entity) {
		
		if(entity==null) {
			return null;
		}else {
			return dao.authentication(entity);
		}
		
	}
	

}
