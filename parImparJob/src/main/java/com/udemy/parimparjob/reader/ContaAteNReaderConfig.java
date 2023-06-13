package com.udemy.parimparjob.reader;

import com.udemy.parimparjob.util.ListaDeInteirosRandomicos;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ContaAteNReaderConfig {

    public static final int QUANTIDADE_ELEMENTOS_INTEIROS = 30000;

    @Bean
    public IteratorItemReader<Integer> itemReader(ListaDeInteirosRandomicos listaDeInteirosRandomicos) {
        List<Integer> elementos = listaDeInteirosRandomicos.gera(QUANTIDADE_ELEMENTOS_INTEIROS);
        return new IteratorItemReader<>(elementos.iterator());
    }
}
