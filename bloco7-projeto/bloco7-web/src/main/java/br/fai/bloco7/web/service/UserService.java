package br.fai.bloco7.web.service;

import java.util.List;

import br.fai.bloco7.model.Usuario;

public interface UserService {

	List<Usuario> readAll();

	Long create(Usuario entity);

	Usuario readById(Long id);

	boolean update(Usuario entity);

	boolean deleteById(Long id);
	
	Usuario authentication (Usuario entity);

}
