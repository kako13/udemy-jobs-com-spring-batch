package com.springbatch.kafkaproducer.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Pessoa {
    private Long id;
    private String nome;
    private String email;
    private String dataNascimento;
    private int idade;

    public boolean isValida() {
        return !Strings.isBlank(nome) && !Strings.isBlank(email) && dataNascimento != null;
    }
}
