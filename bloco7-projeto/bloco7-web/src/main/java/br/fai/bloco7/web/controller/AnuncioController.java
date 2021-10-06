package br.fai.bloco7.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnuncioController {

	@GetMapping("/anuncios")
	public String getAnuncioGridPage() {
		return "anuncio/anuncios-grid";
	}

}
