package br.fai.bloco7.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.fai.bloco7.web.security.provider.Bloco7AuthenticationProvider;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Bloco7AuthenticationProvider authenticationProvider;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

		// sugestao de uso bcrypt*
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/sobre").permitAll()
				.antMatchers("/politica").permitAll()
				.antMatchers("/termos").permitAll()
				.antMatchers("/not-found").permitAll()
				.antMatchers("/pessoa/register").permitAll()
				.antMatchers("/pessoa/login").permitAll()
				.antMatchers("/pessoa/create").permitAll()
				.antMatchers("/anuncios/listar").permitAll()
				.antMatchers("/anuncios/pesquisar").permitAll()
				.antMatchers("/anuncios/detalhes/**").permitAll()
				.antMatchers("/account/password-recover").permitAll()
				.antMatchers("/pessoa/detail/**").hasRole("USUARIO")
				.antMatchers("/pessoa/editar/**").hasRole("USUARIO")
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/pessoa/login-page")
				.loginProcessingUrl("/login").permitAll()
				.defaultSuccessUrl("/")
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.and()
				.exceptionHandling().accessDeniedPage("/not-found");
	}

}
