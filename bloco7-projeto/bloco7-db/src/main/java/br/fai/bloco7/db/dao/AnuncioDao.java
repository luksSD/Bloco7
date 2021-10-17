package br.fai.bloco7.db.dao;

import java.util.List;
import java.util.Map;

import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.model.Recentes;

public interface AnuncioDao {

	List<Anuncio> readAll();

	Anuncio readById(Long id);

	Long create(Anuncio entity);

	boolean update(Anuncio entity);

	boolean delete(Long id);

	List<Anuncio> readByCriteria(Map<String, String> pesquisa);

	List<Anuncio> readByAnuncianteId(Long idAnunciante);

	List<Recentes> readRecents();

}
