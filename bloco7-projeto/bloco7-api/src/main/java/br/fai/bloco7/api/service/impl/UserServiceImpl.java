//Vinicius

package br.fai.bloco7.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.bloco7.api.service.UserService;
import br.fai.bloco7.db.dao.UserDao;
import br.fai.bloco7.model.Usuario;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public List<Usuario> readAll() {

		return dao.readAll();
	}

	@Override
	public Usuario readById(final Long id) {

		return dao.readById(id);
	}

	@Override
	public Long create(final Usuario entity) {

		return dao.create(entity);
	}

	@Override
	public boolean update(final Usuario entity) {

		if (entity == null)
			return false;

		return dao.update(entity);
	}

	@Override
	public boolean delete(final Long id) {

		return dao.delete(id);
	}

	@Override
	public Usuario authentication(Usuario entity) {
		
		if(entity==null) {
			return null;
		}else {
			return dao.authentication(entity);
		}
		
	}

}
