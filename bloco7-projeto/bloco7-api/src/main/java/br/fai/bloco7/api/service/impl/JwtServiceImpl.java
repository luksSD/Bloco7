package br.fai.bloco7.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import br.fai.bloco7.api.security.ApiSecurityCostants;
import br.fai.bloco7.api.service.JwtService;
import br.fai.bloco7.model.Pessoa;
import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceImpl implements JwtService {


	@Override
	public String getJWTToken(Pessoa pessoa) {
try {
			
			final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + pessoa.getTipo()));
			
			final String token = Jwts.builder()
					.setId("BLOCO7")
					.setSubject(pessoa.getEmail())
					.claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 600000))
					.signWith(ApiSecurityCostants.KEY).compact();
			
			return token;
			
			
		} catch (final Exception e) {
			System.err.println(e.getMessage());
			return ApiSecurityCostants.INVALID_TOKEN;
		}
		
	}

}