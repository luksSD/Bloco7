package br.fai.bloco7.web.service;

import java.util.List;

import br.fai.bloco7.model.Anuncio;

public interface AnuncioService {

	List<Anuncio> readAll();

	Long create(Anuncio entity);

	Anuncio readById(Long id);

	boolean update(Anuncio entity);

	boolean deleteById(Long id);

}
