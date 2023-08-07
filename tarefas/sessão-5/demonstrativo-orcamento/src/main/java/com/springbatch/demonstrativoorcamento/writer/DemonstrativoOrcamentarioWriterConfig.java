package com.springbatch.demonstrativoorcamento.writer;

import com.springbatch.demonstrativoorcamento.dominio.GrupoLancamento;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemonstrativoOrcamentarioWriterConfig {

    @Bean
    public ItemWriter<GrupoLancamento> demonstrativoItemWriter() {

        return item -> item.forEach(System.out::println);
    }
}
