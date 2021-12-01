package br.fai.bloco7.db.dao;

import java.util.List;

import br.fai.bloco7.model.Pessoa;

public interface PessoaDao {

	List<Pessoa> readAll();

	Pessoa readById(Long id);

	Long create(Pessoa entity);

	boolean update(Pessoa entity);

	boolean delete(Long id);

//	Pessoa authentication(Pessoa entity);

	boolean updatePassword(Pessoa entity);

	Pessoa validadeEmailAndPassword(final String email, String password);

}
