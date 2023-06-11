package com.udemy.primeiroprojetospringbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class ImprimeOlaTasklet implements Tasklet{

    private final String nome;

    public ImprimeOlaTasklet(@Value("#{jobParameters['nome']}") String nome) {
        this.nome = nome;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.printf("Olá, %s!%n", this.nome);
        return RepeatStatus.FINISHED;
    }
}
