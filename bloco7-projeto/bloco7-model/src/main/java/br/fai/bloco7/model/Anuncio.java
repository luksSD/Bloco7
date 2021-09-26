package br.fai.bloco7.model;

public class Anuncio extends BasePojo {

	private String descricao;
	private String tipo;
	private float preco;
	private String logradouroAnuncio;
	private String numero;
	private String bairro;
	private String cep;
	private Long cidadeId;
	private Long usuarioAnuncianteId;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(final float preco) {
		this.preco = preco;
	}

	public String getLogradouroAnuncio() {
		return logradouroAnuncio;
	}

	public void setLogradouroAnuncio(final String logradouroAnuncio) {
		this.logradouroAnuncio = logradouroAnuncio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(final String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(final String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(final String cep) {
		this.cep = cep;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(final Long cidadeId) {
		this.cidadeId = cidadeId;
	}

	public Long getUsuarioAnuncianteId() {
		return usuarioAnuncianteId;
	}

	public void setUsuarioAnuncianteId(final Long usuarioAnuncianteId) {
		this.usuarioAnuncianteId = usuarioAnuncianteId;
	}

}
