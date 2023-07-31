package com.springbatch.kafkaproducer.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerJobConfig {

    @Bean
    public Job kafkaProducerJob(JobRepository jobRepository,
                                Step kafkaProducerStep) {

        return new JobBuilder("kafkaProducerJob", jobRepository)
                .start(kafkaProducerStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
