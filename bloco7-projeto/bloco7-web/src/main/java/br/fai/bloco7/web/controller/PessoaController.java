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
import br.fai.bloco7.web.security.provider.Bloco7AuthenticationProvider;
import br.fai.bloco7.web.service.AnuncioService;
import br.fai.bloco7.web.service.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

//	public static Long idLogado = null;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private Bloco7AuthenticationProvider authenticationProvider;

	@GetMapping("/list")
	public String getListPage(final Model model, final Anuncio anuncio) {

		final List<Pessoa> pessoa = pessoaService.readAll();

		model.addAttribute("activePage", "conta");
		model.addAttribute("listaDePessoas", pessoa);
//		model.addAttribute("idUsuarioLogado", PessoaController.idLogado);

		return "pessoa/list";
	}

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable("id") final long id, final Model model) {

//		if (PessoaController.idLogado == null) {
//			return "redirect:/pessoa/login";
//		}

		final Anuncio anuncio = new Anuncio();
		final Pessoa pessoa = pessoaService.readById(id);
		model.addAttribute("activePage", "conta");
		model.addAttribute("anuncio", anuncio);
		model.addAttribute("pessoa", pessoa);
//		model.addAttribute("idUsuarioLogado", PessoaController.idLogado);

		return "pessoa/edit";
	}

	@GetMapping("/detail")
	public String getDetailPage(final Model model) {

//		if (PessoaController.idLogado == null) {
//			return "redirect:/pessoa/login";
//		}

		final Anuncio anuncio = new Anuncio();
		model.addAttribute("anuncio", anuncio);

		final Pessoa pessoa = authenticationProvider.getAuthenticatedUser();
		model.addAttribute("pessoa", pessoa);

		final List<Anuncio> anuncios = anuncioService.readByAnuncianteId(pessoa.getId());
		model.addAttribute("listaAnunciosUsuario", anuncios);

		model.addAttribute("activePage", "conta");
//		model.addAttribute("idUsuarioLogado", PessoaController.idLogado);

		return "pessoa/detail";
	}

	@PostMapping("/update")
	public String update(final Pessoa pessoa, final Model model) {

		pessoaService.update(pessoa);

		return getDetailPage(model);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") final Long id, final Model model, final Anuncio anuncio) {

		pessoaService.deleteById(id);

		return getListPage(model, anuncio);
	}

	@GetMapping("/login-page")
	public String getLoginPage(final Pessoa pessoa, final Anuncio anuncio, final Model model) {

		model.addAttribute("activePage", "conta");
		return "pessoa/login";
	}

	@GetMapping("/register")
	public String getRegisterPage(final Pessoa pessoa, final Anuncio anuncio, final Model model) {

//		model.addAttribute("idUsuarioLogado", PessoaController.idLogado);
		model.addAttribute("activePage", "conta");
		return "pessoa/register";
	}

	@PostMapping("/create")
	public String createPessoa(final Pessoa pessoa) {
		final Long id = pessoaService.create(pessoa);
		if (id != -1) {
			return "redirect:/pessoa/login-page";

		}
		return "redirect:/";
	}

	// Retorna pagina de alterar senha
	@GetMapping("/edit/{id}/password")
	public String getPasswordPage(@PathVariable("id") final long id, final Model model) {

//		if (PessoaController.idLogado == null) {
//			return "redirect:/pessoa/login";
//		}

		final Anuncio anuncio = new Anuncio();
		final Pessoa pessoa = pessoaService.readById(id);
		model.addAttribute("activePage", "conta");
		model.addAttribute("anuncio", anuncio);
		model.addAttribute("pessoa", pessoa);
//		model.addAttribute("idUsuarioLogado", PessoaController.idLogado);

		return "pessoa/password";
	}

	@PostMapping("/update-password")
	public String updatePassword(final Pessoa pessoa, final Model model) {

		pessoaService.passwordUpdate(pessoa);

		return getEditPage(pessoa.getId(), model);
	}

//	@PostMapping("/authentication")
//	public String authentication(final Pessoa pessoa, final RedirectAttributes redirectAttributes, final Model model) {
//
//		final Pessoa response = pessoaService.authentication(pessoa);
//
//		if (response == null) {
//			model.addAttribute("activePage", "conta");
//			redirectAttributes.addFlashAttribute("errorMessage", "* Usuário ou senha inválidos");
//			return "redirect:/pessoa/login";
//		} else {
//			final RedirectAttributes pessoaId = redirectAttributes.addFlashAttribute("userId", response.getId());
//			final Long id = Long.valueOf(response.getId());
//			idLogado = id;
//			model.addAttribute("activePage", "conta");
//			return "redirect:/" + getDetailPage(id, model) + "/" + id;
//		}
//	}
//
//	@GetMapping("/logoff")
//	public String logoff() {
//		idLogado = null;
//		return "redirect:/";
//	}
//

}
