package com.springbatch.kafkaconsumer.job;

import com.springbatch.kafkaconsumer.dominio.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@AutoConfigureTestDatabase
@SpringBatchTest
@SpringBootTest
public class JobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @MockBean
    private KafkaItemReader<Long, Pessoa> kafkaItemReader;

    @Test
    public void testaStatusExecucaoDoJob() throws Exception {

        doNothing().when(kafkaItemReader);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(); // "kafkaProducerJob"
        StepExecution next = jobExecution.getStepExecutions().stream().iterator().next();

        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }
}
