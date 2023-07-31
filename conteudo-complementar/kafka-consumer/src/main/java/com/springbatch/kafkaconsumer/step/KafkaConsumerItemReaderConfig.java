package com.springbatch.kafkaconsumer.step;

import com.springbatch.kafkaconsumer.dominio.Pessoa;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConsumerItemReaderConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public KafkaItemReader<Long, Pessoa> kafkaItemReader() throws Exception {

        var props = new Properties();
        props.putAll(kafkaProperties.buildConsumerProperties());

        return new KafkaItemReaderBuilder<Long, Pessoa>()
                .name("pessoa-reader")
                .consumerProperties(props)
                .topic("NEW_PESSOA")
                .partitions(0)
                .saveState(true)
                .build();
    }
}
