package br.fai.bloco7.web.service.imbl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.bloco7.model.Usuario;
import br.fai.bloco7.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<Usuario> readAll() {

		final String endpoint = "http://localhost:8085/api/v1/user/read-all";

		List<Usuario> response = null;

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<Usuario[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET,
					httpEntity, Usuario[].class);

			response = Arrays.asList(requestResponse.getBody());

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public Long create(final Usuario entity) {

		Long id = Long.valueOf(-1);

		final String endpoint = "http://localhost:8085/api/v1/user/create";

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Usuario> httpEntity = new HttpEntity<Usuario>(entity);

			final ResponseEntity<Long> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					Long.class);

			id = responseEntity.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return id;

	}

	@Override
	public Usuario readById(final Long id) {
		final String endpoint = "http://localhost:8085/api/v1/user/read-by-id/" + id;

		Usuario response = null;

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<Usuario> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
					Usuario.class);

			response = requestResponse.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(final Usuario entity) {

		boolean response = false;

		final String endpoint = "http://localhost:8085/api/v1/user/update";

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Usuario> httpEntity = new HttpEntity<Usuario>(entity);

			final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, httpEntity,
					Boolean.class);

			response = responseEntity.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean deleteById(final Long id) {

		boolean response = false;

		final String endpoint = "http://localhost:8085/api/v1/user/delete/" + id;

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<Boolean> requestResponse = restTemplate.exchange(endpoint, HttpMethod.DELETE,
					httpEntity, Boolean.class);

			response = requestResponse.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

}
