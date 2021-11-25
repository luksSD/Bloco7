package br.fai.bloco7.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.fai.bloco7.model.Pessoa;

public class CustomUserDetails extends User {

	public CustomUserDetails(final String username, final String password,
			final Collection<? extends GrantedAuthority> authorities, final Pessoa user) {

		super(username, password, true, true, true, true, authorities);

		this.user = user;

	}

	private final Pessoa user;

	public Pessoa getUsuario() {
		return user;
	}

}
