package br.fai.bloco7.api.security.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.fai.bloco7.api.security.ApiSecurityCostants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException {

		try {

			if (!checkJwtToken(request)) {
				SecurityContextHolder.clearContext();
				return;
			}

			final Jws<Claims> claims = validateToken(request);

			if (claims == null || claims.getBody().get("authorities") == null) {

				SecurityContextHolder.clearContext();

				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Voce esta proibido de acessar este recurso");

				return;
			}

			setupAuthentication(claims.getBody());

		} catch (final Exception e) {

			System.out.println(e.getMessage());

			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Voce esta proibido de acessar este recurso");

		} finally {

			filterChain.doFilter(request, response);

		}

	}

	private boolean checkJwtToken(final HttpServletRequest request) {

		final String authenticationHeader = request.getHeader(ApiSecurityCostants.HEADER);

		if (authenticationHeader == null || !authenticationHeader.startsWith(ApiSecurityCostants.PREFIX)) {
			return false;
		}

		return true;

	}

	private Jws<Claims> validateToken(final HttpServletRequest request) {

		final String JwtToken = request.getHeader(ApiSecurityCostants.HEADER).replace(ApiSecurityCostants.PREFIX, "");

		return Jwts.parserBuilder().setSigningKey(ApiSecurityCostants.KEY).build().parseClaimsJws(JwtToken);

	}

	private void setupAuthentication(final Claims claims) {

		final List<String> authorities = (List<String>) claims.get("authorities");

		final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(),
				null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
