package br.fai.bloco7.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.bloco7.api.service.AnuncioService;
import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.model.Recentes;

@RestController
@RequestMapping("/api/v1/anuncio")
@CrossOrigin(origins = "*")
public class AnuncioRestController {

	@Autowired
	private AnuncioService service;

	@GetMapping("/read-all")
	public ResponseEntity<List<Anuncio>> readAll() {

		return ResponseEntity.ok(service.readAll());

	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Anuncio> readById(@PathVariable("id") final long id) {

		return ResponseEntity.ok(service.readById(id));

	}

	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody final Anuncio anuncio) {

		return ResponseEntity.ok(service.create(anuncio));

	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody final Anuncio anuncio) {

		return ResponseEntity.ok(service.update(anuncio));

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") final long id) {

		return ResponseEntity.ok(service.delete(id));

	}

	@PostMapping("/pesquisar")
	public ResponseEntity<List<Anuncio>> pesquisar(@RequestBody final Anuncio pesquisa) {

		return ResponseEntity.ok(service.readByCriteria(pesquisa));

	}

	@GetMapping("/read-by-anunciante/{id}")
	public ResponseEntity<List<Anuncio>> readByAnunciante(@PathVariable("id") final long idAnunciante) {

		return ResponseEntity.ok(service.readByAnuncianteId(idAnunciante));

	}

	@GetMapping("/read-recents")
	public ResponseEntity<List<Recentes>> readRecents() {

		return ResponseEntity.ok(service.readRecents());

	}

}
