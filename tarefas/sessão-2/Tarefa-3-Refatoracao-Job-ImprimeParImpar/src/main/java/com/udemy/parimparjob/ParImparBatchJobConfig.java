package com.udemy.parimparjob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParImparBatchJobConfig {

	@Bean
	public Job imprimeParImparJob(JobRepository jobRepository, Step step) {
		return new JobBuilder("imprimeParImparAlonsoJob", jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
