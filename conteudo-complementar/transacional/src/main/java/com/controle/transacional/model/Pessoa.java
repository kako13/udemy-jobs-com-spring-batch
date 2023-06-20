package com.controle.transacional.model;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;

@Data
public class Pessoa {
	private int id;
	private String nome;
	private String email;
	private String dataNascimento;
	private int idade;

	public boolean isValida() {
		return !Strings.isBlank(nome) && !Strings.isBlank(email) && dataNascimento != null;
	}
}
