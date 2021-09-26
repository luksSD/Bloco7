package br.fai.bloco7.model;

import java.sql.Timestamp;

public class Mensagem extends BasePojo {

	private Timestamp dataHora;
	private String texto;
	private Long usuarioRemetenteId;
	private Long chatId;

	public Timestamp getDataHora() {
		return dataHora;
	}

	public void setDataHora(final Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(final String texto) {
		this.texto = texto;
	}

	public Long getUsuarioRemetenteId() {
		return usuarioRemetenteId;
	}

	public void setUsuarioRemetenteId(final Long usuarioRemetenteId) {
		this.usuarioRemetenteId = usuarioRemetenteId;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(final Long chatId) {
		this.chatId = chatId;
	}

}
