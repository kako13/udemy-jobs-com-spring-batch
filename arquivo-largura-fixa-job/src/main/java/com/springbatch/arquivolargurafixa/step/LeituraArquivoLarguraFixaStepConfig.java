package com.springbatch.arquivolargurafixa.step;

import com.springbatch.arquivolargurafixa.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class LeituraArquivoLarguraFixaStepConfig {

	public static final int CHUNK_SIZE = 1;

	@Bean
	public Step leituraArquivoLarguraFixaStep(JobRepository jobRepository,
											  PlatformTransactionManager transactionManager,
											  ItemReader<Cliente> leituraArquivoLarguraFixaReader,
											  ItemWriter<? super Object> leituraArquivoLarguraFixaWriter) {
		return new StepBuilder("leituraArquivoLarguraFixaStep", jobRepository)
				.chunk(CHUNK_SIZE, transactionManager)
				.reader(leituraArquivoLarguraFixaReader)
				.writer(leituraArquivoLarguraFixaWriter)
				.build();
	}
}
