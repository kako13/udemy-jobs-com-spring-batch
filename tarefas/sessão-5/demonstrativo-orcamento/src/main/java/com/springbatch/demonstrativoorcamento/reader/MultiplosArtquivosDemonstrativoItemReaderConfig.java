package com.springbatch.demonstrativoorcamento.reader;

import com.springbatch.demonstrativoorcamento.dominio.GrupoLancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
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
    public MultiResourceItemReader<GrupoLancamento> multiplosArquivosDemonstrativoReader(
            @Value("#{jobParameters['arquivosLancamentos']}") Resource[] arquivosClientes,
            DemonstrativoOrcamentarioItemLancamentoReader itemReader) {

        return new MultiResourceItemReaderBuilder<GrupoLancamento>()
                .name("multiplosArquivosDemonstrativoReader")
                .resources(arquivosClientes)
                .delegate(itemReader)
                .build();
    }
}
