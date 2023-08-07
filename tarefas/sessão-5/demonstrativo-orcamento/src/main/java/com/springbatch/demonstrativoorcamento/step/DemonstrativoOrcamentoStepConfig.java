package com.springbatch.demonstrativoorcamento.step;

import com.springbatch.demonstrativoorcamento.reader.DemonstrativoOrcamentarioItemLancamentoReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DemonstrativoOrcamentoStepConfig {

    @Bean
    public Step demonstrativoOrcamentarioStep(JobRepository jobRepository,
                                              PlatformTransactionManager transactionManager,
                                              // Esse aqui lê dos arquivos
//                                              MultiResourceItemReader<GrupoLancamento> delegador,
                                              // Esse aqui lê do banco de dados
                                              DemonstrativoOrcamentarioItemLancamentoReader delegador,
                                              ItemWriter itemWriter) {

        return new StepBuilder("demonstrativoOrcamentarioStep", jobRepository)
                                .chunk(2, transactionManager)
                                .reader(delegador)
                                .writer(itemWriter)
                                .build();
    }
}
