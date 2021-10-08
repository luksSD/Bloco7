package br.fai.bloco7.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.web.service.AnuncioService;
import br.fai.bloco7.web.service.CidadeService;

@Controller
public class HomeController {

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private CidadeService cidadeService;

	@GetMapping("/")
	public String getHomePage(final Model model) {

		final List<Anuncio> anuncios = anuncioService.readAll();

		model.addAttribute("listaDeAnuncio", anuncios);
		return "home";

	}

	@GetMapping("/teste")
	public String getTeste() {
		return "teste";
	}

}
