package com.springbatch.kafkaproducer.writer;

import com.springbatch.kafkaproducer.dominio.Pessoa;
import org.springframework.batch.item.kafka.KafkaItemWriter;
import org.springframework.batch.item.kafka.builder.KafkaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaItemWriterConfig {

    @Autowired
    private KafkaTemplate<Long, Pessoa> template;

    @Bean
    public KafkaItemWriter<Long, Pessoa> kafkaProducerItemWriter() {
        return new KafkaItemWriterBuilder<Long, Pessoa>()
                .itemKeyMapper(Pessoa::getId)
                .kafkaTemplate(template)
                .build();
    }
}
