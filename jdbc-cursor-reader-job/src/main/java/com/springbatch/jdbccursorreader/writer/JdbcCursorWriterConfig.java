package com.springbatch.jdbccursorreader.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.jdbccursorreader.dominio.Cliente;

@Configuration
public class JdbcCursorWriterConfig {
	@Bean
	public ItemWriter<Cliente> jdbcCursorWriter() {
		return clientes -> clientes.forEach(System.out::println);
	}
}
