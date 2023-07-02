package com.springbatch.arquivoxml.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoXMLWriterConfig {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ItemWriter arquivoXMLWriter() {
		return items -> items.forEach(System.out::println);
	}
}
