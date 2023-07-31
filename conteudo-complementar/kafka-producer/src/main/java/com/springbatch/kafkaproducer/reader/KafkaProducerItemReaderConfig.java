package com.springbatch.kafkaproducer.reader;

import com.springbatch.kafkaproducer.dominio.Pessoa;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class KafkaProducerItemReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Pessoa> readerPessoa(@Value("#{jobParameters['arquivoPessoas']}") String arquivoPessoas) {
        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("readerPessoa")
                .resource(new FileSystemResource(arquivoPessoas))
                .comments("--")
                .delimited()
                .delimiter(",")
                .names("nome", "email", "dataNascimento", "idade", "id")
                .targetType(Pessoa.class)
                .build();
    }
}
