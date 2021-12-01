package br.fai.bloco7.api.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public class ApiSecurityCostants {

	public final static String HEADER = "Authorization";

	public final static String PREFIX = "Bearer ";

	public final static String INVALID_TOKEN = "INVALID_TOKEN";

	public final static SecretKey KEY = Keys.hmacShaKeyFor(
			"7f-j&Ckk=coNzZc0y7_4oMP?#TfcYq%fcD0mDpenW2nc!1fGoZ|d?f&RNbDHUX6".getBytes(StandardCharsets.UTF_8));

}
