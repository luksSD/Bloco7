package br.fai.bloco7.api.service;

import java.util.List;

import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.model.Recentes;

public interface AnuncioService {

	List<Anuncio> readAll();

	Anuncio readById(Long id);

	Long create(Anuncio entity);

	boolean update(Anuncio entity);

	boolean delete(Long id);

	List<Anuncio> readByCriteria(Anuncio pesquisa);

	List<Anuncio> readByAnuncianteId(Long idAnunciante);

	List<Recentes> readRecents();
}
