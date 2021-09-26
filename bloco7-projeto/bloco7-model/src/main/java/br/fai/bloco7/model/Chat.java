package br.fai.bloco7.model;

import java.sql.Timestamp;

public class Chat extends BasePojo {

	private Timestamp dataHora;
	private Long anuncioId;
	private Long usuarioInteressadoId;
	private Long usuarioAnuncianteId;

	public Timestamp getDataHora() {
		return dataHora;
	}

	public void setDataHora(final Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public Long getAnuncioId() {
		return anuncioId;
	}

	public void setAnuncioId(final Long anuncioId) {
		this.anuncioId = anuncioId;
	}

	public Long getUsuarioInteressadoId() {
		return usuarioInteressadoId;
	}

	public void setUsuarioInteressadoId(final Long usuarioInteressadoId) {
		this.usuarioInteressadoId = usuarioInteressadoId;
	}

	public Long getUsuarioAnuncianteId() {
		return usuarioAnuncianteId;
	}

	public void setUsuarioAnuncianteId(final Long usuarioAnuncianteId) {
		this.usuarioAnuncianteId = usuarioAnuncianteId;
	}

}
