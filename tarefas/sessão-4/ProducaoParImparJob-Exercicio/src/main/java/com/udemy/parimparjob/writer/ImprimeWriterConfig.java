package com.udemy.parimparjob.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeWriterConfig {
	@Bean
	public ItemWriter<? super Object> imprimeWriter() {
		return items -> items.forEach(System.out::println);
	}
}
