package com.springbatch.kafkaproducer.step;

import com.springbatch.kafkaproducer.dominio.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepKafkaProducerConfig {

    public static final int CHUNK_SIZE = 100;

    @Bean
    public Step kafkaProducerStep(JobRepository jobRepository,
                                  PlatformTransactionManager transactionManager,
                                  FlatFileItemReader<Pessoa> kafkaProducerReader,
                                  ItemWriter kafkaProducerWriter) {
        return new StepBuilder("kafkaProducerStep", jobRepository)
                .chunk(CHUNK_SIZE, transactionManager)
                .reader(kafkaProducerReader)
                .writer(kafkaProducerWriter)
                .build();
    }
}
