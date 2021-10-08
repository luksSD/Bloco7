package br.fai.bloco7.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}

	@GetMapping("/teste")
	public String getTeste() {
		return "teste";
	}

}
