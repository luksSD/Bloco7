package br.fai.bloco7.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.web.service.AnuncioService;

@Controller
@RequestMapping("/anuncios")
public class AnuncioController {

	@Autowired
	private AnuncioService anuncioService;

	@GetMapping("/listar")
	public String getAnuncioGridPage(final Model model) {

		final List<Anuncio> anuncios = anuncioService.readAll();

		model.addAttribute("listaDeAnuncio", anuncios);

		return "anuncio/anuncios-grid";
	}

	@GetMapping("/1")
	public String getSinglePageAnuncio() {
		return "anuncio/anuncio-single";
	}

}
