package com.springbatch.demonstrativoorcamento.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemonstrativoOrcamentarioJobConfig {

    @Bean
    public Job demonstrativoOrcamentarioJob(JobRepository jobRepository,
                                            Step step){
        return new JobBuilder("DemonstrativoOrcamentarioJob", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
