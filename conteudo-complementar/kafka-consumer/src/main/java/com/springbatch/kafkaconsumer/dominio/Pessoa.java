package com.springbatch.kafkaconsumer.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pessoa {
	private Long id;
	private String nome;
	private String email;
	private String dataNascimento;
	private int idade;

	@JsonIgnore
	public boolean isValida() {
		return !Strings.isBlank(nome) && !Strings.isBlank(email) && dataNascimento != null;
	}
}
