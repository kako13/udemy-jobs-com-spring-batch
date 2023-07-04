package com.springbatch.arquivoxml.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ArquivoXMLStepConfig {

	public static final int CHUNK_SIZE = 1;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step arquivoXmlStep(JobRepository jobRepository,
							   PlatformTransactionManager transactionManager,
							   ItemReader xmlItemReader,
							   ItemWriter arquivoXmlWriter) {
		return new StepBuilder("leituraArquivoMultiplosFormatosXmlStep", jobRepository)
				.chunk(CHUNK_SIZE, transactionManager)
				.reader(xmlItemReader)
				.writer(arquivoXmlWriter)
				.build();
	}
}
