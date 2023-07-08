package com.springbatch.skipexception.step;

import com.springbatch.skipexception.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SkipExceptionStepConfig {

	public static final int CHUNK_SIZE = 11;

	@Bean
	public Step skipExceptionStep(JobRepository jobRepository,
								  PlatformTransactionManager transactionManager,
								  ItemReader<Cliente> skipExceptionReader,
								  ItemWriter skipExceptionWriter) {
		return new StepBuilder("skipExceptionStep", jobRepository)
				.chunk(CHUNK_SIZE, transactionManager)
				.reader(skipExceptionReader)
				.writer(skipExceptionWriter)
				.faultTolerant()
				.skip(Exception.class)
				.skipLimit(2)
				.build();
	}
}
