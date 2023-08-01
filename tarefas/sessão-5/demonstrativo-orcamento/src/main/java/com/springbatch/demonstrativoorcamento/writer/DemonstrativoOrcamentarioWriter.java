package com.springbatch.demonstrativoorcamento.writer;

import com.springbatch.demonstrativoorcamento.dominio.Lancamento;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemonstrativoOrcamentarioWriter {

    @Bean
    public ItemWriter<Lancamento> demonstrativoItemWriter() {

        return item -> item.forEach(System.out::println);
    }
}
