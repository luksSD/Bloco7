package br.fai.bloco7.api.service;

import java.util.List;

import br.fai.bloco7.model.Pessoa;

public interface PessoaService {

	List<Pessoa> readAll();

	Pessoa readById(Long id);

	Long create(Pessoa entity);

	boolean update(Pessoa entity);

	boolean delete(Long id);

//	Pessoa authentication(Pessoa entity);

	boolean updatePassword(Pessoa entity);

	Pessoa validateLogin(String encodedData);

}
