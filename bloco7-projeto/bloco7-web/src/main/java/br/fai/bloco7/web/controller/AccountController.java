package br.fai.bloco7.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.bloco7.model.Usuario;
import br.fai.bloco7.web.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;

	@GetMapping("/register-page")
	public String getRegisterPage(final Usuario usuario) {

		return "account/register";
	}

	@PostMapping("/create-user")
	public String create(final Usuario usuario, final Model model) {

		final Long id = userService.create(usuario);
		if (id != -1) {
			return "redirect:/user/detail/" + id;

		}
		model.addAttribute("user", id);
		return "account/register";

	}

}
