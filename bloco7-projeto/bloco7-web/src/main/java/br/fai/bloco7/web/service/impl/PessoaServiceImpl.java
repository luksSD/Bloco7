package br.fai.bloco7.web.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.bloco7.model.Pessoa;
import br.fai.bloco7.web.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Override
	public List<Pessoa> readAll() {

		final String endpoint = "http://localhost:2000/api/v1/pessoa/read-all";

		List<Pessoa> response = null;

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<Pessoa[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
					Pessoa[].class);

			response = Arrays.asList(requestResponse.getBody());

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public Long create(final Pessoa entity) {

		Long id = Long.valueOf(-1);

		final String endpoint = "http://localhost:2000/api/v1/pessoa/create";

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Pessoa> httpEntity = new HttpEntity<Pessoa>(entity);

			final ResponseEntity<Long> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					Long.class);

			id = responseEntity.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return id;

	}

	@Override
	public Pessoa readById(final Long id) {
		final String endpoint = "http://localhost:2000/api/v1/pessoa/read-by-id/" + id;

		Pessoa response = null;

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<Pessoa> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
					Pessoa.class);

			response = requestResponse.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(final Pessoa entity) {

		boolean response = false;

		final String endpoint = "http://localhost:2000/api/v1/pessoa/update";

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Pessoa> httpEntity = new HttpEntity<Pessoa>(entity);

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

		final String endpoint = "http://localhost:2000/api/v1/pessoa/delete/" + id;

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

	@Override
	public Pessoa authentication(final Pessoa entity) {

		Pessoa response = null;

		final String endpoint = "http://localhost:2000/api/v1/pessoa/authentication";

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Pessoa> httpEntity = new HttpEntity<Pessoa>(entity);

			final ResponseEntity<Pessoa> requestResponse = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					Pessoa.class);

			response = requestResponse.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;

	}

	@Override
	public boolean passwordUpdate(final Pessoa entity) {

		boolean response = false;

		final String endpoint = "http://localhost:2000/api/v1/pessoa/update-password";

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Pessoa> httpEntity = new HttpEntity<Pessoa>(entity);

			final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, httpEntity,
					Boolean.class);

			response = responseEntity.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

}
