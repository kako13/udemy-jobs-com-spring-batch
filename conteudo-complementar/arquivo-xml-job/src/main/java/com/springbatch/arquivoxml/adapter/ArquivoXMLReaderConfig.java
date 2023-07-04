package com.springbatch.arquivoxml.adapter;

import com.springbatch.arquivoxml.dominio.Cliente;
import com.springbatch.arquivoxml.step.ArquivoXMLReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class ArquivoXMLReaderConfig implements ArquivoXMLReader {

    @Bean
    @Override
    public StaxEventItemReader<Cliente> xmlItemReader(XStreamMarshaller marshaller) {
        return new StaxEventItemReaderBuilder<Cliente>()
                .name("xmlItemReader")
                .resource(new FileSystemResource("files/XML-clientes-to-read.xml"))
                .addFragmentRootElements("cliente")
                .unmarshaller(marshaller)
                .build();
    }

}
