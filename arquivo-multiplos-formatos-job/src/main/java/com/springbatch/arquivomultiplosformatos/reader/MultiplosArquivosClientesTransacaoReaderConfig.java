package com.springbatch.arquivomultiplosformatos.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MultiplosArquivosClientesTransacaoReaderConfig {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @StepScope
    @Bean
    public MultiResourceItemReader multiplosArquivosClientesTransacaoReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource[] arquivosClientes,
            FlatFileItemReader arquivoClienteTransacaoReader) {
        return new MultiResourceItemReaderBuilder<>()
                .name("multiplosArquivosClientesTransacaoReader")
                .resources(arquivosClientes)
                .delegate(new ArquivoClienteTransacaoReader(arquivoClienteTransacaoReader))
                .build();
    }
}
