package com.springbatch.kafkaconsumer.step;

import com.springbatch.kafkaconsumer.dominio.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WriterTest {

    @MockBean
    private KafkaItemReader<Long, Pessoa> kafkaItemReader;

    @Autowired
    private JdbcBatchItemWriter<Pessoa> itemWriter;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testaPessoaItemWriter() throws Exception {
        Pessoa pessoaA = Pessoa.builder()
                                .id(1L)
                                .nome("Kieth Clark")
                                .email("Kieth_Clark2321@zorer.org")
                                .dataNascimento("2000-05-12 18:26:30")
                                .idade(41)
                                .build();

        Pessoa pessoaB = Pessoa.builder()
                                .id(2L)
                                .nome("Andrea Bailey")
                                .email("Andrea_Bailey3082@corti.com")
                                .dataNascimento("1887-06-15 03:10:50")
                                .idade(29)
                                .build();

        itemWriter.write(new Chunk<Pessoa>(List.of(pessoaA, pessoaB)));

        List<Pessoa> pessoasResult = jdbcTemplate.query("select id, nome, email, data_nascimento, idade from pessoa",
                                                        new BeanPropertyRowMapper<>(Pessoa.class));

        assertEquals(pessoaA, pessoasResult.get(0));
        assertEquals(pessoaB, pessoasResult.get(1));

        System.out.println(pessoasResult.get(0));
        System.out.println(pessoasResult.get(1));
    }
}