package com.springbatch.arquivoxml.dominio;

import lombok.Data;

@Data
public class Transacao {
	public String id;
	public String descricao;
	public Double valor;

	@Override
	public String toString() {
		return "Transacao{" + "id='" + id + "'" + ", descricao='" + descricao + "'" + ", valor='" + valor + "'" + '}';
	}
}
