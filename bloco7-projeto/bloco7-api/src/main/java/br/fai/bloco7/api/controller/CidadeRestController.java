package br.fai.bloco7.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.bloco7.api.service.CidadeService;
import br.fai.bloco7.model.Cidade;

@RestController
@RequestMapping("/api/v1/cidade")
@CrossOrigin(origins = "*")
public class CidadeRestController {

	@Autowired
	private CidadeService service;

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Cidade> readById(@PathVariable("id") final long id) {

		return ResponseEntity.ok(service.readById(id));

	}

}
