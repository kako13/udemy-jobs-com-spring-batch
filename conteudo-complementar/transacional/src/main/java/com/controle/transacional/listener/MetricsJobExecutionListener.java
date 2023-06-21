package com.controle.transacional.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.observability.BatchMetrics;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class MetricsJobExecutionListener implements JobExecutionListener {

    private LocalDateTime startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = LocalDateTime.now();

        LocalDateTime startTime = jobExecution.getStartTime();

        System.out.println();
        System.out.println();
        System.out.println("**********************************************************");
        System.out.println("Job " + jobExecution.getJobInstance().getJobName() + " iniciado em " + startTime);
        System.out.println("**********************************************************");
        System.out.println();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LocalDateTime endTime = LocalDateTime.now();
        long totalItems = jobExecution.getStepExecutions()
                .stream()
                .mapToLong(StepExecution::getWriteCount)
                .sum();

        Duration duration = BatchMetrics.calculateDuration(startTime, endTime);
        String metricas = BatchMetrics.formatDuration(duration);

        System.out.println();
        System.out.println("*********************** Métricas *************************");
        System.out.println("Total Items Processed: \t\t\t\t\t" + totalItems);
        System.out.println("Job Duration: \t\t\t\t\t\t\t" + metricas + " ms");
        System.out.println("**********************************************************");
        System.out.println();
        System.out.println();

    }
}