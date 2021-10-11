package br.fai.bloco7.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.model.Cidade;
import br.fai.bloco7.model.Pessoa;
import br.fai.bloco7.model.Usuario;
import br.fai.bloco7.web.service.AnuncioService;
import br.fai.bloco7.web.service.CidadeService;
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

	@Autowired
	private CidadeService cidadeService;

//	Metodo para exibição da pagina de anuncios
	@GetMapping("/listar")
	public String getAnuncioGridPage(final Model model) {

		final List<Anuncio> anuncios = anuncioService.readAll();

		model.addAttribute("listaDeAnuncio", anuncios);
		model.addAttribute("activePage", "anuncio");

		return "anuncio/anuncios-grid";
	}

//	Metodo para detalhes de anuncio especifico
	@GetMapping("/detalhes/{id}")
	public String getDetailPage(@PathVariable("id") final long idAnuncio, final Model model) {

		final Anuncio anuncio = anuncioService.readById(idAnuncio);
		model.addAttribute("anuncio", anuncio);

		final Usuario user = userService.readById(anuncio.getUsuarioAnuncianteId());
		model.addAttribute("usuario", user);

		final Pessoa pessoa = pessoaService.readById(user.getPessoaId());
		model.addAttribute("pessoa", pessoa);

		final Cidade cidade = cidadeService.readById(anuncio.getCidadeId());
		model.addAttribute("cidade", cidade);

		model.addAttribute("activePage", "anuncio");

		return "anuncio/anuncio-single";
	}

	@GetMapping("/pesquisar")
	public String getPaginaPesquisar(final Anuncio anuncio, final Model model) {

		final List<Anuncio> pesquisa = anuncioService.pesquisar(anuncio);

		model.addAttribute("pesquisar", pesquisa);

		return null;
	}

}
