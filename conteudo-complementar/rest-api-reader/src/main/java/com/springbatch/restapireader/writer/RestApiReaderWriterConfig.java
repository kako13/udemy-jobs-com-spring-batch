package com.springbatch.restapireader.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestApiReaderWriterConfig {

    @Bean
    public ItemWriter restApiWriter() {
        return items -> items.forEach(System.out::println);
    }
}
