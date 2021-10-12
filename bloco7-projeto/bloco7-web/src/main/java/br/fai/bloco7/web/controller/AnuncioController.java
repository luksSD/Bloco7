package br.fai.bloco7.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/register")
	public String getRegisterPage(final Anuncio anuncio) {
		return "anuncio/register";
	}

//	Metodo para exibição da pagina de anuncios
	@GetMapping("/listar")
	public String getAnuncioGridPage(final Model model) {

		final List<Anuncio> anuncios = anuncioService.readAll();

		model.addAttribute("listaDeAnuncio", anuncios);
		model.addAttribute("activePage", "anuncio");

		return "anuncio/anuncios-grid";
	}

	@GetMapping("/listar-logado")
	public String getAnuncioGridPageLogado(final Model model) {

		final List<Anuncio> anuncios = anuncioService.readAll();

		model.addAttribute("listaDeAnuncio", anuncios);

		return "anuncio/anuncios-grid-logado";
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

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable("id") final long id, final Model model) {

		final Anuncio anuncio = anuncioService.readById(id);
		model.addAttribute("anuncio", anuncio);

		return "anuncio/edit";
	}

	@PostMapping("/create")
	public String createAnuncio(final Anuncio anuncio, final Model model) {

		final Long id = anuncioService.create(anuncio);

		if (id != -1) {
			return "redirect:/anuncios/listar-logado";
		}
		model.addAttribute("anuncio", id);
		return "redirect:/dashboard/";
	}

	@PostMapping("/update")
	public String update(final Anuncio anuncio, final Model model) {

		anuncioService.update(anuncio);

		return getDetailPage(anuncio.getId(), model);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") final Long id, final Model model) {

		anuncioService.deleteById(id);

		return getAnuncioGridPage(model);
	}

//	Metodo para realizar pesquisa
	@GetMapping("/pesquisar")
	public String search(final Anuncio pesquisa, final Model model) {

		// Cria lista do tipo anuncios para receber o resultado do metodo pesquisar()
		final List<Anuncio> anuncio = anuncioService.pesquisar(pesquisa);

		// Injeta o resultado da pesquisa na view
		model.addAttribute("listaDeAnuncio", anuncio);
		// Indicador de pagina ativa
		model.addAttribute("activePage", "anuncio");

		// Retorna pagina de pesquisa
		return "anuncio/anuncios-pesquisa";
	}

}
