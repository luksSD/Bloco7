package br.fai.bloco7.web.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.model.Recentes;
import br.fai.bloco7.web.service.AnuncioService;
import br.fai.bloco7.web.service.RestService;

@Service
public class AnuncioServiceImpl implements AnuncioService {

	@Override
	public List<Anuncio> readAll() {

		final String endpoint = "http://localhost:2000/api/v1/anuncio/read-all";

		List<Anuncio> response = null;

		try {
			final HttpHeaders headers = RestService.getRequestHeaders();

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

			final ResponseEntity<Anuncio[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET,
					httpEntity, Anuncio[].class);

			response = Arrays.asList(requestResponse.getBody());

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public Long create(final Anuncio entity) {

		Long id = Long.valueOf(-1);

		final String endpoint = "http://localhost:2000/api/v1/anuncio/create";

		try {

			final HttpHeaders headers = RestService.getRequestHeaders();

			final RestTemplate restTemplate = new RestTemplate();
			final HttpEntity<Anuncio> httpEntity = new HttpEntity<Anuncio>(entity, headers);
			final ResponseEntity<Long> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					Long.class);
			id = responseEntity.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return id;

	}

	@Override
	public Anuncio readById(final Long id) {
		final String endpoint = "http://localhost:2000/api/v1/anuncio/read-by-id/" + id;

		Anuncio response = null;

		try {
			final HttpHeaders headers = RestService.getRequestHeaders();

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

			final ResponseEntity<Anuncio> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
					Anuncio.class);

			response = requestResponse.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(final Anuncio entity) {
		boolean response = false;

		final String endpoint = "http://localhost:2000/api/v1/anuncio/update";

		try {
			final HttpHeaders headers = RestService.getRequestHeaders();

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Anuncio> httpEntity = new HttpEntity<Anuncio>(entity, headers);

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

		final String endpoint = "http://localhost:2000/api/v1/anuncio/delete/" + id;

		try {
			final HttpHeaders headers = RestService.getRequestHeaders();

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

			final ResponseEntity<Boolean> requestResponse = restTemplate.exchange(endpoint, HttpMethod.DELETE,
					httpEntity, Boolean.class);

			response = requestResponse.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public List<Anuncio> readByCriteria(final Anuncio pesquisa) {

		final String endpoint = "http://localhost:2000/api/v1/anuncio/pesquisar";

		List<Anuncio> response = null;

		try {
			final HttpHeaders headers = RestService.getRequestHeaders();

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Anuncio> httpEntity = new HttpEntity<Anuncio>(pesquisa, headers);

			final ResponseEntity<Anuncio[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.POST,
					httpEntity, Anuncio[].class);

			response = Arrays.asList(requestResponse.getBody());

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public List<Anuncio> readByAnuncianteId(final Long id) {

		final String endpoint = "http://localhost:2000/api/v1/anuncio/read-by-anunciante/" + id;

		List<Anuncio> response = null;

		try {
			final HttpHeaders headers = RestService.getRequestHeaders();

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Long> httpEntity = new HttpEntity<Long>(id, headers);

			final ResponseEntity<Anuncio[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET,
					httpEntity, Anuncio[].class);

			response = Arrays.asList(requestResponse.getBody());

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;

	}

	@Override
	public List<Recentes> readRecents() {

		final String endpoint = "http://localhost:2000/api/v1/anuncio/read-recents";

		List<Recentes> response = null;

		try {
			final HttpHeaders headers = RestService.getRequestHeaders();

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

			final ResponseEntity<Recentes[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET,
					httpEntity, Recentes[].class);

			response = Arrays.asList(requestResponse.getBody());

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

}
