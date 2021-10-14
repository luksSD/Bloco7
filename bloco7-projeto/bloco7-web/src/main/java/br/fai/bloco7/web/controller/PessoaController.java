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
import br.fai.bloco7.model.Pessoa;
import br.fai.bloco7.web.service.AnuncioService;
import br.fai.bloco7.web.service.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private AnuncioService anuncioService;

	@GetMapping("/list")
	public String getListPage(final Model model, final Anuncio anuncio) {

		final List<Pessoa> pessoa = pessoaService.readAll();

		model.addAttribute("listaDePessoas", pessoa);

		return "pessoa/list";
	}

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable("id") final long id, final Model model, final Anuncio anuncio) {

		final Pessoa pessoa = pessoaService.readById(id);
		model.addAttribute("pessoa", pessoa);

		return "pessoa/edit";
	}

	@GetMapping("/detail/{id}")
	public String getDetailPage(@PathVariable("id") final long id, final Model model) {

		final Anuncio anuncio = new Anuncio();
		final Pessoa pessoa = pessoaService.readById(id);
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("anuncio", anuncio);

		return "pessoa/detail";
	}

	@PostMapping("/update")
	public String update(final Pessoa pessoa, final Model model) {

		pessoaService.update(pessoa);

		return getDetailPage(pessoa.getId(), model);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") final Long id, final Model model, final Anuncio anuncio) {

		pessoaService.deleteById(id);

		return getListPage(model, anuncio);
	}

	@GetMapping("/login")
	public String login(final Pessoa pessoa, final Anuncio anuncio) {

		return "pessoa/login";
	}

	@GetMapping("/register")
	public String getRegisterPage(final Pessoa pessoa, final Anuncio anuncio) {
		return "pessoa/register";
	}

	@PostMapping("/create")
	public String createPessoa(final Pessoa pessoa) {
		final Long id = pessoaService.create(pessoa);
		if (id != -1) {
			return "redirect:/pessoa/list";

		}
		return "redirect:/";
	}

}
