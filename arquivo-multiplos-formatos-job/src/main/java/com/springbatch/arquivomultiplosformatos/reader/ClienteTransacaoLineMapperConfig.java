package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ClienteTransacaoLineMapperConfig {


    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public LineMapper lineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMappers());
        return lineMapper;
    }

    @SuppressWarnings({"rawtypes"})
    private Map<String, FieldSetMapper> fieldSetMappers() {
        return Map.of(
                "0*", fieldSetMapper(Cliente.class),
                "1*", fieldSetMapper(Transacao.class)
        );
    }

    @SuppressWarnings("rawtypes")
    private FieldSetMapper fieldSetMapper(Class classe) {
        BeanWrapperFieldSetMapper<Object> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(classe);
        return fieldSetMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        return Map.of(
                "0*", clienteLineTokenizer(),
                "1*", transacaoLineTokenizer()
        );
    }

    private LineTokenizer clienteLineTokenizer() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("nome", "sobrenome", "idade", "email");

        // O primeiro elemento, zero '0' é reservado para o prefixo de cada linha (identificação do tipo de registro).
        delimitedLineTokenizer.setIncludedFields(1, 2, 3, 4);
        return delimitedLineTokenizer;
    }

    private LineTokenizer transacaoLineTokenizer() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("id", "descricao", "valor");

        // O primeiro elemento, zero '0' é reservado para o prefixo de cada linha (identificação do tipo de registro).
        delimitedLineTokenizer.setIncludedFields(1, 2, 3);
        return delimitedLineTokenizer;
    }
}
