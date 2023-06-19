package com.udemy.parimparjob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ParImparJobConfig {

	@Bean
	public Job imprimeParImparJob(JobRepository jobRepository, Step step) {
		return new JobBuilder("imprimeParImparJob", jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
