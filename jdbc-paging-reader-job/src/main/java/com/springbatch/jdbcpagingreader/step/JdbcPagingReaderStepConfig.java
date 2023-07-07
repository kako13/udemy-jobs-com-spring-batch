package com.springbatch.jdbcpagingreader.step;



import com.springbatch.jdbcpagingreader.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JdbcPagingReaderStepConfig {
	
	@Bean
	public Step jdbcPagingReaderStep(JobRepository jobRepository,
									 PlatformTransactionManager transactionManager,
									 ItemReader<Cliente> jdbcPagingReader,
									 ItemWriter jdbcPagingWriter) {
		return new StepBuilder("jdbcPagingReaderStep", jobRepository)
				.chunk(1, transactionManager)
				.reader(jdbcPagingReader)
				.writer(jdbcPagingWriter)
				.build();
	}
}
