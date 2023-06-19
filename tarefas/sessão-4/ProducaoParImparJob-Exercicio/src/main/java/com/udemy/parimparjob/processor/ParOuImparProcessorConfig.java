package com.udemy.parimparjob.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParOuImparProcessorConfig {
	@Bean
	public ItemProcessor<? super Object, ?> parOuImparProcessor() {
		return item -> (Integer) item % 2 == 0
				? String.format("Item %s é Par", item)
				: String.format("Item %s é Impar", item);
	}
}
