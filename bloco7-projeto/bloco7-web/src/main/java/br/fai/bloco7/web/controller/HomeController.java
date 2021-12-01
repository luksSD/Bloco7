package br.fai.bloco7.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.model.Recentes;
import br.fai.bloco7.web.service.AnuncioService;
import br.fai.bloco7.web.service.CidadeService;

@Controller
public class HomeController {

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private CidadeService cidadeService;

	@GetMapping("/")
	public String getHomePage(final Anuncio pesquisa, final Model model) {

		final List<Anuncio> anuncios = anuncioService.readAll();
		model.addAttribute("listaDeAnuncio", anuncios);

		final List<Recentes> recentes = anuncioService.readRecents();
		model.addAttribute("anunciosRecentes", recentes);

		model.addAttribute("activePage", "home");
//		model.addAttribute("idUsuarioLogado", PessoaController.idLogado);

		return "home";

	}

	@GetMapping("/sobre")
	public String getSobre(final Anuncio anuncio) {
		return "/sobre";
	}

	@GetMapping("/politica")
	public String getPolitica(final Anuncio anuncio) {
		return "/politica";
	}

	@GetMapping("/termos")
	public String getTermos(final Anuncio anuncio) {
		return "/termos";
	}

	@GetMapping("/not-found")
	public String getNotFoundPage(final Model model) {

		final Anuncio anuncio = new Anuncio();
		model.addAttribute("anuncio", anuncio);

		return "exception/not-found";
	}

}
