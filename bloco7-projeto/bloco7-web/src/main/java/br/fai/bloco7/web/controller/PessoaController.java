package br.fai.bloco7.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.bloco7.model.Pessoa;
import br.fai.bloco7.model.Usuario;
import br.fai.bloco7.web.service.PessoaService;
import br.fai.bloco7.web.service.UserService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public String getListPage(final Model model) {

		final List<Pessoa> pessoa = pessoaService.readAll();

		model.addAttribute("listaDePessoas", pessoa);

		return "pessoa/list";
	}

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable("id") final long id, final Model model) {

		final Pessoa pessoa = pessoaService.readById(id);
		model.addAttribute("pessoa", pessoa);

		return "pessoa/edit";
	}

	@GetMapping("/detail/{id}")
	public String getDetailPage(@PathVariable("id") final long id, final Model model) {

		final Pessoa pessoa = pessoaService.readById(id);
		model.addAttribute("pessoa", pessoa);

		return "pessoa/detail";
	}

	@PostMapping("/update")
	public String update(final Pessoa pessoa, final Model model) {

		pessoaService.update(pessoa);

		return getDetailPage(pessoa.getId(), model);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") final Long id, final Model model) {

		pessoaService.deleteById(id);

		return getListPage(model);
	}

	@GetMapping("/login")
	public String login(final Usuario usuario) {
		return "user/login";
	}

	@GetMapping("/register")
	public String getRegisterPage(final Pessoa pessoa) {
		return "pessoa/register";
	}

	@GetMapping("/create")
	public String createPessoa(final Pessoa pessoa) {
		final Long id = pessoaService.create(pessoa);
		if (id != -1) {
			return "redirect:/pessoa/list";

		}
		return "redirect:/";
	}

	@PostMapping("/authentication")
	public String authentication(final Usuario usuario) {

		userService.authentication(usuario);
		return "redirect:/dashboard/";
	}

}
