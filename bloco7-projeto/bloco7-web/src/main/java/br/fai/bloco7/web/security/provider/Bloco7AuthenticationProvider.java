package br.fai.bloco7.web.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.fai.bloco7.model.Pessoa;
import br.fai.bloco7.web.security.CustomUserDetails;
import br.fai.bloco7.web.service.PessoaService;

@Component
public class Bloco7AuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PessoaService pessoaService;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		final String email = authentication.getName();
		final String password = authentication.getCredentials().toString();

		System.out.println("Email: " + email + "  Password: " + password);

		final Pessoa pessoa = pessoaService.validateEmailAndPassword(email, password);

		if (pessoa == null) {
			return null;
		}

		final List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + pessoa.getTipo()));

		final UserDetails principal = new CustomUserDetails(email, password, grantedAuthorityList, pessoa);

		return new UsernamePasswordAuthenticationToken(principal, password, grantedAuthorityList);
	}

	@Override
	public boolean supports(final Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public Pessoa getAuthenticatedUser() {

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final CustomUserDetails userDatails = (CustomUserDetails) authentication.getPrincipal();

		return userDatails.getUsuario();

	}

}
