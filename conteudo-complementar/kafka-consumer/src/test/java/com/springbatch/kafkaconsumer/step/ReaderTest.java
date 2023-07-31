package com.springbatch.kafkaconsumer.step;

import com.springbatch.kafkaconsumer.dominio.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReaderTest {

    @MockBean
    private KafkaItemReader<Long, Pessoa> kafkaItemReader;

    @Test
    public void testaKafkaItemReader() {
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

        when(kafkaItemReader.read()).thenReturn(pessoaA, pessoaB);

        Pessoa pessoaLidaA = kafkaItemReader.read();
        Pessoa pessoaLidaB = kafkaItemReader.read();

        verify(kafkaItemReader, times(2)).read();

        assertEquals(pessoaA, pessoaLidaA);
        assertEquals(pessoaB, pessoaLidaB);

        System.out.println(pessoaLidaA);
        System.out.println(pessoaLidaB);
    }
}
