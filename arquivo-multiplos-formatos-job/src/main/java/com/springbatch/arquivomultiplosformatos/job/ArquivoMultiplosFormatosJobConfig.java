package com.springbatch.arquivomultiplosformatos.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoMultiplosFormatosJobConfig {

	@Bean
	public Job arquivoMultiplosFormatosJob(JobRepository jobRepository, Step leituraArquivoMultiplosFormatosStep) {
		return new JobBuilder("arquivoMultiplosFormatosJob", jobRepository)
				.start(leituraArquivoMultiplosFormatosStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}