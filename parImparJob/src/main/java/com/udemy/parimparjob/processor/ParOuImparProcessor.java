package com.udemy.parimparjob.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParOuImparProcessor {

    @Bean
    public ItemProcessor<? super Object, ?> itemProcessor() {
        return item -> (Integer) item % 2 == 0
                ? String.format("Item %s é par", item)
                : String.format("Item %s é ímpar", item);
    }
}
