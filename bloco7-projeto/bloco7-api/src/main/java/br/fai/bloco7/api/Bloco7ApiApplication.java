package br.fai.bloco7.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "br.fai.bloco7.*" })
public class Bloco7ApiApplication {

	public static void main(final String[] args) {
		SpringApplication.run(Bloco7ApiApplication.class, args);
	}

}
