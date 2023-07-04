package com.springbatch.selecaojobporparametro.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParImparBatchConfig {

	@Bean
	public Job imprimeParImparJob(JobRepository jobRepository,
								  @Qualifier("ImprimeParImparStep") Step step) {

		return new JobBuilder("imprimeParImparJob", jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
