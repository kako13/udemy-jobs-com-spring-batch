package com.springbatch.kafkaproducer.reader;

import com.springbatch.kafkaproducer.JobParametersTestUtils;
import com.springbatch.kafkaproducer.dominio.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase
@SpringBootTest
public class ReaderTest {

    @Autowired
    private FlatFileItemReader<Pessoa> readerPessoa;

    /*
     * Foi necessá rio criar um contexto de execução para o teste,
     * pois o reader usa o StepScope para pegar o "#{jobParameters['arquivoPessoas']}"
     */
    public StepExecution getStepExecution() {
        return MetaDataInstanceFactory.createStepExecution(JobParametersTestUtils.getJobParameters());
    }

    @Test
    public void testaOrdemIdsEConteudoTipoPessoa() throws Exception {
        /*
        *  StepScopeTestUtils se encarrega de criar um contexto de execução para o teste
        */
        StepScopeTestUtils.doInStepScope(getStepExecution(), () -> {
            readerPessoa.open(new ExecutionContext());
            Pessoa pessoa;
            int id = 1;
            while ((pessoa = readerPessoa.read()) != null) {
                System.out.println(pessoa);
                assertEquals(id, pessoa.getId());
                assertEquals(Pessoa.class, pessoa.getClass());
                id++;
            }
            return null;
        });
    }
}

