package br.fai.bloco7.api.service;

import java.util.List;

import br.fai.bloco7.model.Usuario;

public interface UserService {

	List<Usuario> readAll();

	Usuario readById(Long id);

	Long create(Usuario entity);

	boolean update(Usuario entity);

	boolean delete(Long id);
	
	Usuario authentication(Usuario entity);

}