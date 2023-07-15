package com.springbatch.restapireader.reader;

import com.springbatch.restapireader.LinguagemDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestApiItemReaderConfig {

    @Bean
    public ItemReader<LinguagemDTO> itemReader(Environment environment,
                                               RestTemplate restTemplate) {
        return new RestItemReader(environment.getRequiredProperty("spring.linguagens.api.url"),
                restTemplate
        );
    }

}
