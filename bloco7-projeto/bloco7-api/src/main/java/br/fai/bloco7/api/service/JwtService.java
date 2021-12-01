package br.fai.bloco7.api.service;

import br.fai.bloco7.model.Pessoa;

public interface JwtService {

	String getJWTToken(Pessoa pessoa);

}
