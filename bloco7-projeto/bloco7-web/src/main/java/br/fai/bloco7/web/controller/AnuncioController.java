package br.fai.bloco7.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.model.Pessoa;
import br.fai.bloco7.model.Usuario;
import br.fai.bloco7.web.service.AnuncioService;
import br.fai.bloco7.web.service.PessoaService;
import br.fai.bloco7.web.service.UserService;

@Controller
@RequestMapping("/anuncios")
public class AnuncioController {

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private UserService userService;

	@Autowired
	private PessoaService pessoaService;

	@GetMapping("/listar")
	public String getAnuncioGridPage(final Model model) {

		final List<Anuncio> anuncios = anuncioService.readAll();

		model.addAttribute("listaDeAnuncio", anuncios);

		return "anuncio/anuncios-grid";
	}

	@GetMapping("/detalhes/{id}")
	public String getDetailPage(@PathVariable("id") final long idAnuncio, final Model anuncioModel,
			final Model usuarioModel, final Model pessoaModel) {

		final Anuncio anuncio = anuncioService.readById(idAnuncio);
		anuncioModel.addAttribute("anuncio", anuncio);

		final Usuario user = userService.readById(anuncio.getUsuarioAnuncianteId());
		usuarioModel.addAttribute("usuario", user);

		final Pessoa pessoa = pessoaService.readById(user.getPessoaId());
		pessoaModel.addAttribute("pessoa", pessoa);

		return "anuncio/anuncio-single";
	}

}
