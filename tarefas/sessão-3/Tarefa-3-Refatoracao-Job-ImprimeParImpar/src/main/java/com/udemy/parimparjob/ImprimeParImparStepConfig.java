package com.udemy.parimparjob;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ImprimeParImparStepConfig {

    public static final int TAMANHO_CHUNK = 2;

    @Bean
    public Step imprimeParImparStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("imprimeParImparAlonsoStep", jobRepository)
                .chunk(TAMANHO_CHUNK, transactionManager)
                .reader(contaAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }


    private IteratorItemReader<Integer> contaAteDezReader() {
        List<Integer> numerosDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return new IteratorItemReader<>(numerosDeUmAteDez.iterator());
    }

    private ItemProcessor<? super Object, ?> parOuImparProcessor() {
        return item -> (Integer) item % 2 == 0
                ? String.format("Item %s é Par", item)
                : String.format("Item %s é Ímpar", item);
    }

    private ItemWriter<? super Object> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
