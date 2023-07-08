package com.springbatch.skipexception.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Cliente {
	private String nome;
	private String sobrenome;
	private String idade;
	private String email;


	@Override
	public String toString() {
		return "Cliente{" +
	                "nome='" + nome + "'" +
	                ", sobrenome ='" + sobrenome + "'" +
	                ", idade='" + idade + "'" +
	                ", email='" + email + "'" +
	                '}';
	}
}