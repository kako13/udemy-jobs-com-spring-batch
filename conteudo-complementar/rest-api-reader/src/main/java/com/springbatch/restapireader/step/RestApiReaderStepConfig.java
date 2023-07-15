package com.springbatch.restapireader.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class RestApiReaderStepConfig {


    public static final int CHUNK_SIZE = 1;

    @Bean
    public Step restApiReaderStep(JobRepository jobRepository,
                                  PlatformTransactionManager transactionManager,
                                  ItemReader restApiReaderReader,
                                  ItemWriter restApiReaderWriter) {
        return new StepBuilder("restApiReaderStep", jobRepository)
                .chunk(CHUNK_SIZE, transactionManager)
                .reader(restApiReaderReader)
                .writer(restApiReaderWriter)
                .build();
    }
}

