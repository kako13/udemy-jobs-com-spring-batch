package com.springbatch.arquivoxml.adapter;

import com.springbatch.arquivoxml.dominio.Cliente;
import com.springbatch.arquivoxml.step.ArquivoXMLWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class ArquivoXMLWriterConfig implements ArquivoXMLWriter {

	@Autowired
	private XStreamMarshaller marshaller;

	@Bean
	public StaxEventItemWriter<Cliente> xmlItemWriter() {

		return new StaxEventItemWriterBuilder<Cliente>()
				.name("xmlItemWriter")
				.resource(new FileSystemResource("files/XML-clientes-writed.xml"))
				.marshaller(marshaller)
				.rootTagName("clientes")
				.overwriteOutput(true)
				.build();
	}
}
