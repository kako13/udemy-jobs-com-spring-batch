package com.springbatch.kafkaproducer.job;

import com.springbatch.kafkaproducer.JobParametersTestUtils;
import com.springbatch.kafkaproducer.dominio.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.kafka.KafkaItemWriter;
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
    private KafkaItemWriter<Long, Pessoa> kafkaItemWriter;

    @Test
    public void testaJob() throws Exception {
        doNothing().when(kafkaItemWriter);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(JobParametersTestUtils.getJobParameters()); // "kafkaProducerJob"
        StepExecution next = jobExecution.getStepExecutions().stream().iterator().next();
        assertEquals(10, next.getReadCount());
        assertEquals(10, next.getWriteCount());
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }

}
