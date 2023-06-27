package com.springbatch.arquivodelimitado.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoDelimitadoJobConfig {

	@Bean
	public Job arquivoDelimitadoJob(JobRepository jobRepository, Step leituraArquivoDelimitadoStep) {
		return new JobBuilder("arquivoDelimitadoJob", jobRepository)
				.start(leituraArquivoDelimitadoStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
