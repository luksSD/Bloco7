package br.fai.bloco7.web.service;

import java.util.List;

import br.fai.bloco7.model.Pessoa;

public interface PessoaService {

	List<Pessoa> readAll();

	Long create(Pessoa entity);

	Pessoa readById(Long id);

	boolean update(Pessoa entity);

	boolean deleteById(Long id);

	Pessoa validateEmailAndPassword(String email, String password);

	boolean passwordUpdate(Pessoa entity);

}
