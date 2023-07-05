package com.springbatch.arquivojson.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoJsonJobConfig {

	@Bean
	public Job arquivoJsonJob(JobRepository jobRepository,
							  Step leituraArquivoMultiplosFormatosStep) {
		return new JobBuilder("arquivoJsonJob", jobRepository)
				.start(leituraArquivoMultiplosFormatosStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}