package com.springbatch.kafkaproducer.step;

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
public class StepTest {

    @MockBean
    private KafkaItemWriter<Long, Pessoa> kafkaItemWriter;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testaStep() throws Exception {

        // Passar o chunk sem permitir a postagem no tópico
        doNothing().when(kafkaItemWriter);

        // Execução do step isoladamente usando o JobLauncherTestUtils
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("kafkaProducerStep",
                                                                    JobParametersTestUtils.getJobParameters());

        StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
        assertEquals(BatchStatus.COMPLETED, stepExecution.getStatus());
        assertEquals(10, stepExecution.getReadCount());
        assertEquals(10, stepExecution.getWriteCount());
    }
}
