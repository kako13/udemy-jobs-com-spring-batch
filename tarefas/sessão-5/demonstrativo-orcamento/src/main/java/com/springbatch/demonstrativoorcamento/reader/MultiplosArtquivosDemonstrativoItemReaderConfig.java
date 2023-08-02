package com.springbatch.demonstrativoorcamento.reader;

import com.springbatch.demonstrativoorcamento.dominio.Lancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MultiplosArtquivosDemonstrativoItemReaderConfig {

    @StepScope
    @Bean
    public MultiResourceItemReader<Lancamento> multiplosArquivosDemonstrativoReader(
            @Value("#{jobParameters['arquivosLancamentos']}") Resource[] arquivosClientes,
            FlatFileItemReader<Lancamento> itemReader) {

        return new MultiResourceItemReaderBuilder<Lancamento>()
                .name("multiplosArquivosDemonstrativoReader")
                .resources(arquivosClientes)
//                .delegate(new DemonstrativoOrcamentarioItemLancamentoReader(itemReader))
                .build();
    }
}
