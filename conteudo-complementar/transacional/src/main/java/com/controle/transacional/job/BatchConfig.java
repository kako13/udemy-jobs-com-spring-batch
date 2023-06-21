package com.controle.transacional.job;

import com.controle.transacional.listener.MetricsJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Bean
    public Job jobPessoa(JobRepository jobRepository,
                         Step step,
                         MetricsJobExecutionListener jobListener) {
        return new JobBuilder("jobPessoa", jobRepository)
                .listener(jobListener)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
