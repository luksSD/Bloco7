package br.fai.bloco7.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.bloco7.api.service.JwtService;
import br.fai.bloco7.api.service.PessoaService;
import br.fai.bloco7.db.dao.PessoaDao;
import br.fai.bloco7.model.Pessoa;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaDao dao;

	@Autowired
	private JwtService jwtService;

	@Override
	public List<Pessoa> readAll() {

		return dao.readAll();
	}

	@Override
	public Pessoa readById(final Long id) {

		return dao.readById(id);
	}

	@Override
	public Long create(final Pessoa entity) {

		return dao.create(entity);
	}

	@Override
	public boolean update(final Pessoa entity) {

		if (entity == null)
			return false;

		return dao.update(entity);
	}

	@Override
	public boolean delete(final Long id) {

		return dao.delete(id);
	}

//	@Override
//	public Pessoa authentication(final Pessoa entity) {
//
//		if (entity == null) {
//			return null;
//		} else {
//			return dao.authentication(entity);
//		}
//
//	}

	@Override
	public boolean updatePassword(final Pessoa entity) {

		if (entity == null)
			return false;

		return dao.updatePassword(entity);
	}

	@Override
	public Pessoa validateLogin(final String encodedData) {
		final Map<CREDENCIAIS, String> credentialsMap = decodeAndGetEmailAndPassword(encodedData);

		if (credentialsMap == null || credentialsMap.size() != 2) {
			return null;

		}

		final String email = credentialsMap.get(CREDENCIAIS.EMAIL);
		final String password = credentialsMap.get(CREDENCIAIS.SENHA);

		final Pessoa pessoa = dao.validadeEmailAndPassword(email, password);

		if (pessoa == null) {
			return null;
		}

		final String token = jwtService.getJWTToken(pessoa);

		pessoa.setSenha(null);
		pessoa.setToken(token);

		return pessoa;

	}

	private enum CREDENCIAIS {

		EMAIL, SENHA,
	}

	private Map<CREDENCIAIS, String> decodeAndGetEmailAndPassword(final String encodedData) {

		// Basic + dados
		final String[] splittedData = encodedData.split("Basic ");

		if (splittedData.length != 2) {
			return null;
		}

		// dados
		final byte[] decodedBytes = Base64.getDecoder().decode(splittedData[1]);

		try {

			// Email=email;Password=senha
			final String decodedString = new String(decodedBytes, "utf-8");

			final String[] firstPart = decodedString.split("Email=");

			if (firstPart.length != 2) {
				return null;
			}

			// nome_usuario;Password=senha
			final String[] credentials = firstPart[1].split(";Password=");

			if (credentials.length != 2) {
				return null;
			}

			final Map<CREDENCIAIS, String> credentialsMap = new HashMap<CREDENCIAIS, String>();
			credentialsMap.put(CREDENCIAIS.EMAIL, credentials[0]);
			credentialsMap.put(CREDENCIAIS.SENHA, credentials[1]);

			return credentialsMap;

		} catch (final UnsupportedEncodingException e) {
			System.out.println(e.getMessage());

			return null;
		}
	}

}
