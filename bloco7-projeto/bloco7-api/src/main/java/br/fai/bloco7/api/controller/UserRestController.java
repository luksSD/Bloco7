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

import br.fai.bloco7.api.service.UserService;
import br.fai.bloco7.model.Usuario;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	private UserService service;

	@GetMapping("/read-all")
	public ResponseEntity<List<Usuario>> readAll() {

		return ResponseEntity.ok(service.readAll());

	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Usuario> readById(@PathVariable("id") final long id) {

		return ResponseEntity.ok(service.readById(id));

	}

	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody final Usuario usuario) {

		return ResponseEntity.ok(service.create(usuario));

	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody final Usuario usuario) {

		return ResponseEntity.ok(service.update(usuario));

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") final long id) {

		return ResponseEntity.ok(service.delete(id));

	}
	
	
	@GetMapping("/authentication/{usuario}")
	public ResponseEntity<Usuario> authentication(@PathVariable("usuario") Usuario usuario){
		return ResponseEntity.ok(service.authentication(usuario));
	}
	
	
}
