package br.fai.bloco7.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Anuncio> pesquisar(final Anuncio pesquisa) {

		final Map<String, String> criteria = new HashMap<String, String>();

		if (pesquisa.getDescricao().equals("")) {
			criteria.put("descricao", "%%");
		} else {
			criteria.put("descricao", pesquisa.getDescricao());
		}

		criteria.put("tipo_propiedade", pesquisa.getTipo_propriedade());
		criteria.put("quartos", String.valueOf(pesquisa.getQuartos()));
		criteria.put("vaga_garagem", String.valueOf(pesquisa.getVaga_garagem()));
		criteria.put("banheiros", String.valueOf(pesquisa.getBanheiros()));

		return dao.readByCriteria(criteria);
	}

}
