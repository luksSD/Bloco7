package br.fai.bloco7.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.bloco7.model.Usuario;
import br.fai.bloco7.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public String getListPage(final Model model) {

		final List<Usuario> users = userService.readAll();

		model.addAttribute("listaDeUsuarios", users);

		return "user/list";
	}

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable("id") final long id, final Model model) {

		final Usuario user = userService.readById(id);
		model.addAttribute("usuario", user);

		return "user/edit";
	}

	@GetMapping("/detail/{id}")
	public String getDetailPage(@PathVariable("id") final long id, final Model model) {

		final Usuario user = userService.readById(id);
		model.addAttribute("usuario", user);

		return "user/detail";
	}

	@PostMapping("/update")
	public String update(final Usuario usuario, final Model model) {

		userService.update(usuario);

		return getDetailPage(usuario.getId(), model);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") final Long id, final Model model) {

		userService.deleteById(id);

		return getListPage(model);
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	

}
