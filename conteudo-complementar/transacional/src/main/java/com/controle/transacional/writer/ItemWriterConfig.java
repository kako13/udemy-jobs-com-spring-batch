package com.controle.transacional.writer;

import com.controle.transacional.model.Pessoa;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class ItemWriterConfig {

    @Bean
    public ItemWriter<Pessoa> writer(@Qualifier("writerPessoaDb1") ItemWriter<Pessoa> writerPessoaDb1,
                                     @Qualifier("writerPessoaDb2") ItemWriter<Pessoa> writerPessoaDb2) {
        return new CompositeItemWriter<Pessoa>(List.of(writerPessoaDb1, writerPessoaDb2));
    }

    @Bean
    public ItemWriter<Pessoa> writerPessoaDb1(@Qualifier("xaDB1") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Pessoa>()
                .dataSource(dataSource)
                .sql("INSERT INTO pessoa (id, nome, email, data_nascimento, idade) VALUES (:id, :nome, :email, :dataNascimento, :idade)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
    @Bean
    public ItemWriter<Pessoa> writerPessoaDb2(@Qualifier("xaDB2") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Pessoa>()
                .dataSource(dataSource)
                .sql("INSERT INTO pessoa (id, nome, email, data_nascimento, idade) VALUES (:id, :nome, :email, :dataNascimento, :idade)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
