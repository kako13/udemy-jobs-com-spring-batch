package com.controle.transacional.reader;

import com.controle.transacional.model.Pessoa;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ItemReaderConfig {

    @Bean
    public ItemReader<Pessoa> readerPessoa() {
        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("readerPessoa")
                .resource(new FileSystemResource("files/pessoas.csv"))
                .comments("--")
                .delimited()
                .delimiter(",")
                .names("nome", "email", "dataNascimento", "idade", "id")
                .targetType(Pessoa.class)
                .build();
    }
}
