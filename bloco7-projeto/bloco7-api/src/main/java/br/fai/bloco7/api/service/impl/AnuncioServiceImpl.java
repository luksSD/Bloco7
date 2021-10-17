package br.fai.bloco7.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.bloco7.api.service.AnuncioService;
import br.fai.bloco7.db.dao.AnuncioDao;
import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.model.Recentes;

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
	public List<Anuncio> readByCriteria(final Anuncio pesquisa) {

		final Map<String, String> criteria = new HashMap<String, String>();

		if (pesquisa.getDescricao().equals("")) {
			criteria.put("descricao", "ilike '%%'");
		} else {
			criteria.put("descricao", "ilike '%" + pesquisa.getDescricao() + "%'");
		}

		if (!pesquisa.getTipo_propriedade().equals("TODOS")) {
			criteria.put("tipo_propriedade", "= '" + pesquisa.getTipo_propriedade() + "'");
		}

		if (pesquisa.getQuartos() != 0) {
			criteria.put("quartos", "= " + String.valueOf(pesquisa.getQuartos()));
		}

		if (pesquisa.getVaga_garagem() != 0) {
			criteria.put("vaga_garagem", "= " + String.valueOf(pesquisa.getVaga_garagem()));
		}

		if (pesquisa.getBanheiros() != 0) {
			criteria.put("banheiros", "= " + String.valueOf(pesquisa.getBanheiros()));
		}

		if (pesquisa.getBanheiros() != 0) {
			criteria.put("banheiros", "= " + String.valueOf(pesquisa.getBanheiros()));
		}

		if (!pesquisa.getNomeCidade().equals("TODOS")) {
			criteria.put("nome", "= " + pesquisa.getNomeCidade());
		}

		if (!pesquisa.getStatus().equals("TODOS")) {
			criteria.put("status", "= " + pesquisa.getStatus());
		}

		return dao.readByCriteria(criteria);
	}

	@Override
	public List<Anuncio> readByAnuncianteId(final Long idAnunciante) {

		return dao.readByAnuncianteId(idAnunciante);
	}

	@Override
	public List<Recentes> readRecents() {

		return dao.readRecents();
	}

}
