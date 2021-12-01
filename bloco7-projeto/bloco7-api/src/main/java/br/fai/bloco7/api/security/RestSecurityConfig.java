package br.fai.bloco7.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.fai.bloco7.api.security.filter.JWTAuthorizationFilter;

@EnableWebSecurity
@Configuration
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.
		csrf().disable()
		.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/v1/pessoa/login").permitAll()
			.antMatchers(HttpMethod.POST, "/api/v1/pessoa/create").permitAll()
			.antMatchers(HttpMethod.GET, "/api/v1/anuncio/read-all").permitAll()
			.antMatchers(HttpMethod.GET, "/api/v1/anuncio/read-by-id/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/v1/cidade/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/v1/anuncio/pesquisar").permitAll()
			.antMatchers(HttpMethod.GET, "/api/v1/anuncio/read-recents").permitAll()
		.anyRequest().authenticated();


}

}
