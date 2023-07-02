package com.springbatch.arquivoxml.reader;

import com.springbatch.arquivoxml.dominio.Cliente;
import com.springbatch.arquivoxml.dominio.Transacao;
import com.thoughtworks.xstream.XStream;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.Map;

@Configuration
public class ArquivoXMLReaderConfig {

    @Bean
    public StaxEventItemReader xmlItemReader() {
        return new StaxEventItemReaderBuilder<Cliente>()
                .name("xmlItemReader")
                .resource(new FileSystemResource("files/XML-clientes.xml"))
                .addFragmentRootElements("cliente")
                .unmarshaller(clienteMarshaller())
                .build();
    }

    public XStreamMarshaller clienteMarshaller() {
        Map<String, Class> aliases = Map.of(
                "cliente", Cliente.class,
                "nome", String.class,
                "sobrenome", String.class,
                "idade", Integer.class,
                "email", String.class,
                "transacao", Transacao.class
        );

        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setAliases(aliases);

        return marshaller;
    }

    @Bean
    public XStream xstream() {
        XStream xstream = new XStream();
        xstream.allowTypesByWildcard(new String[] {
                "com.springbatch.arquivoxml.dominio.*"
        });
        return xstream;
    }
}
