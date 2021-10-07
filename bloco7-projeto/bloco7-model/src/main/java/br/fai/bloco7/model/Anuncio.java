package br.fai.bloco7.model;

public class Anuncio extends BasePojo {

	private String descricao;
	private int quartos;
	private int banheiros;
	private int vaga_garagem;
	private String tipo_propriedade;
	private String status;
	private int area;
	private float preco;
	private String endereco;
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

	public int getQuartos() {
		return quartos;
	}

	public void setQuartos(final int quartos) {
		this.quartos = quartos;
	}

	public int getBanheiros() {
		return banheiros;
	}

	public void setBanheiros(final int banheiros) {
		this.banheiros = banheiros;
	}

	public int getVaga_garagem() {
		return vaga_garagem;
	}

	public void setVaga_garagem(final int vaga_garagem) {
		this.vaga_garagem = vaga_garagem;
	}

	public String getTipo_propriedade() {
		return tipo_propriedade;
	}

	public void setTipo_propriedade(final String tipo_propriedade) {
		this.tipo_propriedade = tipo_propriedade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public int getArea() {
		return area;
	}

	public void setArea(final int area) {
		this.area = area;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(final float preco) {
		this.preco = preco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(final String endereco) {
		this.endereco = endereco;
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
