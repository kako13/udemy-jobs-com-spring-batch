package com.springbatch.arquivomultiplosformatos.dominio;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cliente {
	private String nome;
	private String sobrenome;
	private String idade;
	private String email;
	private List<Transacao> transacoes = new ArrayList<>();

	@Override
	public String toString() {
		return "Cliente{"
				+ "nome='" + nome + "'"
				+ ", sobrenome ='" + sobrenome + "'"
				+ ", idade='" + idade + "'"
				+ ", email='" + email + "'"
				+ ", email='" + email + "'"
				+ (transacoes.isEmpty()
					? ""
					: ", transacoes=" + transacoes)
				+ '}';
	}
}
