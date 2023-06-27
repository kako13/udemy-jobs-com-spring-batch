package com.springbatch.arquivolargurafixa.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoLarguraFixaJobConfig {

	@Bean
	public Job arquivoLarguraFixaJob(Step leituraArquivoLarguraFixaStep, JobRepository jobRepository) {
		return new JobBuilder("arquivoLarguraFixaJob", jobRepository)
				.start(leituraArquivoLarguraFixaStep)
//				.incrementer(new RunIdIncrementer())
				.build();
	}
}
