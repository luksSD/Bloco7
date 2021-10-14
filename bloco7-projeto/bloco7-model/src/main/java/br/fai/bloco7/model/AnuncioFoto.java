package br.fai.bloco7.model;

public class AnuncioFoto extends BasePojo {

	private String fotoPath;
	private Long anuncioId;

	public String getFotoPath() {
		return fotoPath;
	}

	public void setFotoPath(final String fotoPath) {
		this.fotoPath = fotoPath;
	}

	public Long getAnuncioId() {
		return anuncioId;
	}

	public void setAnuncioId(final Long anuncioId) {
		this.anuncioId = anuncioId;
	}

}
