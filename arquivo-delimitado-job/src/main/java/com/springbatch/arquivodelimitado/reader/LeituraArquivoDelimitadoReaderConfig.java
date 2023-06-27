package com.springbatch.arquivodelimitado.reader;

import com.springbatch.arquivodelimitado.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoDelimitadoReaderConfig {

	@StepScope
	@Bean
	public FlatFileItemReader<Cliente> leituraArquivoDelimitadoReader(@Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes) {
		// TODO Implementar leitor de arquivo delimitado.
		return new FlatFileItemReaderBuilder<Cliente>()
				.name("leituraArquivoDelimitadoReader")
				.resource(arquivoClientes)
				.delimited()
				.delimiter(",")
				.names("nome", "sobrenome", "idade", "email")
				.targetType(Cliente.class)
				.build();
    }
}
