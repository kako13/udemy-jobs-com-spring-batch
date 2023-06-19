package com.udemy.parimparjob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ParImparStepConfig {

	public static final int TAMANHO_CHUNK = 1;

	@Bean
	public Step ImprimeParImparStep(JobRepository jobRepository,
									PlatformTransactionManager transactionManager,
									IteratorItemReader<Integer> itemReader,
									ItemProcessor<? super Object, ?> parOuImparProcessor,
									ItemWriter<? super Object> imprimeWriter) {
		return new StepBuilder("imprimeParImparStep", jobRepository)
				.chunk(TAMANHO_CHUNK, transactionManager)
				.reader(itemReader)
				.processor(parOuImparProcessor)
				.writer(imprimeWriter)
				.build();
	}
}
