package com.springbatch.demonstrativoorcamento.reader;


import com.springbatch.demonstrativoorcamento.dominio.Lancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class DemonstrativoOrcamentarioItemReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Lancamento> fileItemReader(@Value("#{jobParameters['arquivosLancamentos']}") Resource lancamentos) {

        return new FlatFileItemReaderBuilder<Lancamento>()
                                    .resource(lancamentos)
                                    .name("fileItemReader")
                                    .delimited()
                                        .delimiter(",")
                                        .names("codigoNatureza", "descricaoNatureza", "descricaoItem", "data", "valor")
                                    .targetType(Lancamento.class)
                                    .build();
    }

    @Bean
    public JdbcCursorItemReader<Lancamento> dataBaseItemReader(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Lancamento>()
                .name("dataBaseItemReader")
                .dataSource(dataSource)
                .sql("select * from lancamento")
                .rowMapper(new BeanPropertyRowMapper<>(Lancamento.class))
                .build();
    }
}
