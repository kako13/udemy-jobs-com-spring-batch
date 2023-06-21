package com.controle.transacional.step;

import com.controle.transacional.listener.ContadorChunkListener;
import com.controle.transacional.listener.TotalizadorStepListener;
import com.controle.transacional.model.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PessoaStepConfig {

    public static final int TAMANHO_CHUNK = 200;

    @Bean
    public Step stepPessoa(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           ItemReader<Pessoa> reader,
                           ItemWriter writer,
                           TotalizadorStepListener stepListener,
                           ContadorChunkListener chunkListener) {
        return new StepBuilder("stepPessoa", jobRepository)
                .chunk(TAMANHO_CHUNK, transactionManager)
                .listener(stepListener)
                .listener(chunkListener)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
