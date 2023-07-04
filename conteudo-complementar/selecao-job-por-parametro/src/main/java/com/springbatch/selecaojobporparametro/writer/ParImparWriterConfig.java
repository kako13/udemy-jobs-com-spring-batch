package com.springbatch.selecaojobporparametro.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParImparWriterConfig {

    @Bean
    public ItemWriter<? super Object> intemWriter() {
        return itens -> itens.forEach(System.out::println);
    }

}
