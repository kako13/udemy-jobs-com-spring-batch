package com.springbatch.skipexception.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.skipexception.dominio.Cliente;

@Configuration
public class SkipExceptionWriterConfig {
	@Bean
	public ItemWriter<Cliente> printWriter() {
		return clientes -> clientes.forEach(System.out::println);
	}
}
