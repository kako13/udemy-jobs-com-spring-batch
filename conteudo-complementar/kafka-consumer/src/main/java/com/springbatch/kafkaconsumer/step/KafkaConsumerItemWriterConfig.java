package com.springbatch.kafkaconsumer.step;

import com.springbatch.kafkaconsumer.dominio.Pessoa;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class KafkaConsumerItemWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Pessoa> pessoaItemWriter(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Pessoa>()
                .dataSource(dataSource)
                .sql("insert into pessoa " +
                        "(id, nome, email, data_nascimento, idade) " +
                        "values (:id, :nome, :email, :dataNascimento, :idade)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
