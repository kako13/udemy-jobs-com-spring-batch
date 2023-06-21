package com.controle.transacional.listener;

import lombok.SneakyThrows;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.batch.core.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TotalizadorStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        StepExecutionListener.super.beforeStep(stepExecution);

        System.out.println();
        System.out.println("**********************************************************");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        StepExecutionListener.super.beforeStep(stepExecution);

        System.out.println();
        System.out.println("**********************************************************");
        System.out.println();

        return null;
    }
}
