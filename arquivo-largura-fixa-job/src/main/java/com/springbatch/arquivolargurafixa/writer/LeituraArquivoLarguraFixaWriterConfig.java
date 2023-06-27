package com.springbatch.arquivolargurafixa.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {
	@Bean
	public ItemWriter<? super Object> leituraArquivoLarguraFixaWriter() {
//		TODO: Para execução normal, descomente o código abaixo:
		return items -> items.forEach(System.out::println);

//	TODO: Para estimular interrupção de execução, descomente o código abaixo:
//	return items -> {
//		for (Object item : items) {
//
//			Cliente cliente = (Cliente) item;
//
//			if (cliente.getNome().equals("Maria"))
//				throw new Exception("Erro ao processar o elemento: " + cliente);
//			else
//				System.out.println("Cliente ignorado: " + cliente);
//		}
//	};
	}
}
