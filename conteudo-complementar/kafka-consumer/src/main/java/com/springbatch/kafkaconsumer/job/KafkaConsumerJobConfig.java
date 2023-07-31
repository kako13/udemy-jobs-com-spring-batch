package com.springbatch.kafkaconsumer.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConsumerJobConfig {

    @Bean
    public Job kafkaConsumerJob(JobRepository jobRepository,
                                Step kafkaConsumerStep) {

        return new JobBuilder("kafkaConsumerJob", jobRepository)
                .start(kafkaConsumerStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
