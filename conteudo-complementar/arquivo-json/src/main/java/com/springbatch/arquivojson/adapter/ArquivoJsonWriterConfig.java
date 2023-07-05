package com.springbatch.arquivojson.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springbatch.arquivojson.dominio.ClienteListWrapper;
import com.springbatch.arquivojson.step.ArquivoJsonWriter;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoJsonWriterConfig implements ArquivoJsonWriter {

	@Bean
	public JsonFileItemWriter<ClienteListWrapper> jsonItemWriter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return new JsonFileItemWriterBuilder<ClienteListWrapper>()
				.name("jsonItemWriter")
				.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>(objectMapper))
				.resource(new FileSystemResource("files/JSON-clientes-writed.json"))
				.build();
	}
}
