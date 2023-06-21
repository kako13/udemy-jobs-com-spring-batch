package com.controle.transacional.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

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
