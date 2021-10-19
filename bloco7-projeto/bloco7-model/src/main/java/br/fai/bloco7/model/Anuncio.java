package br.fai.bloco7.model;

public class Anuncio extends BasePojo {

	private String descricao;
	private int quartos;
	private int banheiros;
	private int vaga_garagem;
	private String tipo_propriedade;
	private String status;
	private int area;
	private String preco;
	private String endereco;
	private String bairro;
	private String cep;
	private Long cidadeId;
	private Long usuarioAnuncianteId;
	private String nomeCidade;
	private String foto1;
	private String foto2;
	private String foto3;
	private String foto4;
	private String foto5;
	private String video;

	public String getVideo() {
		return video;
	}

	public void setVideo(final String video) {
		this.video = video;
	}

	public void setFoto3(final String foto3) {
		this.foto3 = foto3;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(final String nomeCidade) {
		this.nomeCidade = nomeCidade;
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

	public String getPreco() {
		return preco;
	}

	public void setPreco(final String preco) {
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

	public String getFoto1() {
		return foto1;
	}

	public void setFoto1(final String foto1) {
		this.foto1 = foto1;
	}

	public String getFoto2() {
		return foto2;
	}

	public void setFoto2(final String foto2) {
		this.foto2 = foto2;
	}

	public String getFoto3() {
		return foto3;
	}

	public String getFoto4() {
		return foto4;
	}

	public void setFoto4(final String foto4) {
		this.foto4 = foto4;
	}

	public String getFoto5() {
		return foto5;
	}

	public void setFoto5(final String foto5) {
		this.foto5 = foto5;
	}

}
