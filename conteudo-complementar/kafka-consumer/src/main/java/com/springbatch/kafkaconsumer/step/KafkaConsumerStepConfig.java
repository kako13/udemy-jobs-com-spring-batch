package com.springbatch.kafkaconsumer.step;

import com.springbatch.kafkaconsumer.dominio.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class KafkaConsumerStepConfig {


    public static final int CHUNK_SIZE = 200;

    @Bean
    public Step kafkaConsumerStep(JobRepository jobRepository,
                                  @Qualifier("jtaTransactionManagerApp") PlatformTransactionManager transactionManager,
                                  KafkaItemReader<Long, Pessoa> kafkaConsumerReader,
                                  ItemWriter kafkaConsumerWriter) {

        return new StepBuilder("kafkaConsumerStep", jobRepository)
                .chunk(CHUNK_SIZE, transactionManager)
                .reader(kafkaConsumerReader)
                .writer(kafkaConsumerWriter)
                .build();
    }
}
