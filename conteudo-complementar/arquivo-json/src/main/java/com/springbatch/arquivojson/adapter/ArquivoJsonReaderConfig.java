package com.springbatch.arquivojson.adapter;

import com.springbatch.arquivojson.dominio.ClienteListWrapper;
import com.springbatch.arquivojson.step.ArquivoJsonReader;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoJsonReaderConfig implements ArquivoJsonReader {

    @Bean
    @Override
    public JsonItemReader<ClienteListWrapper> jsonItemReader() {
        return new JsonItemReaderBuilder<ClienteListWrapper>()
                .name("jsonItemReader")
                .jsonObjectReader(new JacksonJsonObjectReader<>(ClienteListWrapper.class))
                .resource(new FileSystemResource("files/JSON-clientes-to-read.json"))
                .build();
    }

}
