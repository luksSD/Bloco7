package br.fai.bloco7.web.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.bloco7.model.Cidade;
import br.fai.bloco7.web.service.CidadeService;
import br.fai.bloco7.web.service.RestService;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Override
	public Cidade readById(final Long id) {
		final String endpoint = "http://localhost:2000/api/v1/cidade/read-by-id/" + id;

		Cidade response = null;

		try {
			final HttpHeaders headers = RestService.getRequestHeaders();

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

			final ResponseEntity<Cidade> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
					Cidade.class);

			response = requestResponse.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

}
