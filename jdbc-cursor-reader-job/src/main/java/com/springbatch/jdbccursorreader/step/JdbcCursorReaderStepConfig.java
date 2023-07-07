package com.springbatch.jdbccursorreader.step;

import com.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JdbcCursorReaderStepConfig {

	public static final int CHUNK_SIZE = 1;

	@Bean
	public Step jdbcCursorReaderStep(JobRepository jobRepository,
									 PlatformTransactionManager transactionManager,
									 ItemReader<Cliente> jdbcCursorReader,
									 ItemWriter jdbcCursorWriter) {
		return new StepBuilder("jdbcCursorReaderStep", jobRepository)
				.chunk(CHUNK_SIZE, transactionManager)
				.reader(jdbcCursorReader)
				.writer(jdbcCursorWriter)
				.build();
	}
}
