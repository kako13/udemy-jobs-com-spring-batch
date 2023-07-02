package com.springbatch.arquivoxml.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoXmlJobConfig {

	@Bean
	public Job arquivoXmlJob(JobRepository jobRepository,
											  Step leituraArquivoMultiplosFormatosStep) {
		return new JobBuilder("arquivoXmlJob", jobRepository)
				.start(leituraArquivoMultiplosFormatosStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}