package br.fai.bloco7.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.bloco7.web.service.UserService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String pageLayout() {
		return "/dashboard/index";
	}
	
	
}
