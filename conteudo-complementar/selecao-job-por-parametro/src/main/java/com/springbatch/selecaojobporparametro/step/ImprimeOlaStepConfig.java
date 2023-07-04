package com.springbatch.selecaojobporparametro.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ImprimeOlaStepConfig {

    @Bean
    public Step imprimeOlaStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, Tasklet imprimeOlaTasklet) {
        return new StepBuilder("primeiroStep", jobRepository)
                .tasklet(imprimeOlaTasklet, transactionManager)
                .build();
    }
}