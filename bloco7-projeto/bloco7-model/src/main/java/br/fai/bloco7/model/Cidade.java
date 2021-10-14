package br.fai.bloco7.model;

public class Cidade extends BasePojo {

	private String estado;
	private String nome;

	public String getEstado() {
		return estado;
	}

	public void setEstado(final String estado) {
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

}
