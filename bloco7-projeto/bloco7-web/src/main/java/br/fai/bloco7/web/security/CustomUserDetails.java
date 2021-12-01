package br.fai.bloco7.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.fai.bloco7.model.Pessoa;

public class CustomUserDetails extends User {

	public CustomUserDetails(final String email, final String password,
			final Collection<? extends GrantedAuthority> authorities, final Pessoa pessoa) {

		super(email, password, true, true, true, true, authorities);

		this.pessoa = pessoa;

	}

	private final Pessoa pessoa;

	public Pessoa getUsuario() {
		return pessoa;
	}

}