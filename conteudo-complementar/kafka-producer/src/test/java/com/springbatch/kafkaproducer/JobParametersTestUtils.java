package com.springbatch.kafkaproducer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import java.time.LocalDateTime;

public class JobParametersTestUtils {

    /*
     * Foi necessá rio criar um contexto de execução para o teste,
     * pois o reader usa o StepScope para pegar o "#{jobParameters['arquivoPessoas']}"
     */
    private static final String VALUE_PARAMETER = "src/test/resources/pessoas_test.csv";
    public static final String KEY_PARAMETER = "arquivoPessoas";

    public static JobParameters getJobParameters() {
        return new JobParametersBuilder()
                .addString(KEY_PARAMETER, VALUE_PARAMETER)
                .addLocalDateTime("identificadorUnico", LocalDateTime.now())
                .toJobParameters();
    }
}