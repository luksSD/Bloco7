package br.fai.bloco7.web.service.imbl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.web.service.AnuncioService;

@Service
public class AnuncioServiceImpl implements AnuncioService {

	@Override
	public List<Anuncio> readAll() {

		final String endpoint = "http://localhost:2000/api/v1/anuncio/read-all";

		List<Anuncio> response = null;

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Anuncio readById(final Long id) {
		final String endpoint = "http://localhost:2000/api/v1/anuncio/read-by-id/" + id;

		Anuncio response = null;

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(final Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
