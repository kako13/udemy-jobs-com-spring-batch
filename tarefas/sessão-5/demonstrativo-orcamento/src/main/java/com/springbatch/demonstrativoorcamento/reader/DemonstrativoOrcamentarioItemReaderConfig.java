package com.springbatch.demonstrativoorcamento.reader;


import com.springbatch.demonstrativoorcamento.dominio.Despesa;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class DemonstrativoOrcamentarioItemReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Despesa> fileItemReader(@Value("#{jobParameters['arquivosDespesas']}") Resource lancamentos) {

        return new FlatFileItemReaderBuilder<Despesa>()
                                    .resource(lancamentos)
                                    .name("fileItemReader")
                                    .delimited()
                                        .delimiter(",")
                                        .names("codigoNatureza", "descricaoNatureza", "descricaoItem", "data", "valor")
                                    .targetType(Despesa.class)
                                    .build();
    }
}
