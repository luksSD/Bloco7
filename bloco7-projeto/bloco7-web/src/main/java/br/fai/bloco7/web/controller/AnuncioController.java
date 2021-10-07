package br.fai.bloco7.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anuncios")
public class AnuncioController {

	@GetMapping("/listar")
	public String getAnuncioGridPage() {
		return "anuncio/anuncios-grid";
	}

	@GetMapping("/1")
	public String getSinglePageAnuncio() {
		return "anuncio/anuncio-single";
	}

}
