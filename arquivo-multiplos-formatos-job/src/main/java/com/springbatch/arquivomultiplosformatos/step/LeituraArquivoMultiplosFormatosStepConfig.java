package com.springbatch.arquivomultiplosformatos.step;

import com.springbatch.arquivomultiplosformatos.reader.ArquivoClienteTransacaoReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class LeituraArquivoMultiplosFormatosStepConfig {

	public static final int CHUNK_SIZE = 1;

	@Bean
	public Step leituraArquivoMultiplosFormatosStep(JobRepository jobRepository,
													PlatformTransactionManager transactionManager,
													FlatFileItemReader leituraArquivoMultiplosFormatosReader,
													ItemWriter leituraArquivoMultiplosFormatosItemWriter) {
		return new StepBuilder("leituraArquivoMultiplosFormatosStep", jobRepository)
				.chunk(CHUNK_SIZE, transactionManager)
				.reader(new ArquivoClienteTransacaoReader(leituraArquivoMultiplosFormatosReader))
				.writer(leituraArquivoMultiplosFormatosItemWriter)
				.build();
	}
}
