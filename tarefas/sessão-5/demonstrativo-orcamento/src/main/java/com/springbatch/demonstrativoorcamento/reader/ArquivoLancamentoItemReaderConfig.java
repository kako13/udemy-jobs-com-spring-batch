package com.springbatch.demonstrativoorcamento.reader;


import com.springbatch.demonstrativoorcamento.dominio.GrupoLancamento;
import com.springbatch.demonstrativoorcamento.dominio.Lancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

@Configuration
public class ArquivoLancamentoItemReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<GrupoLancamento> fileItemReader(@Value("#{jobParameters['arquivosLancamentos']}") Resource lancamentos) {

        System.out.println(">>>>>>> Lendo dos arquivos! <<<<<<<");

        return new FlatFileItemReaderBuilder<GrupoLancamento>()
                                    .resource(lancamentos)
                                    .name("fileItemReader")
                                    .delimited()
                                        .delimiter(",")
                                        .names("codigoNatureza",
                                                "descricaoNatureza",
                                                "descricaoLancamento",
                                                "data",
                                                "valor")
                                    .fieldSetMapper(fieldSetMapper())
//                              .targetType(GrupoLancamento.class) // Não deve ser utilizado ao utilizar fieldSetMapper
                                    .build();
    }

    private FieldSetMapper<GrupoLancamento> fieldSetMapper() {
        return new FieldSetMapper<GrupoLancamento>() {
            @Override
            public GrupoLancamento mapFieldSet(FieldSet fieldSet) throws BindException {
                return GrupoLancamento.builder()
                        .codigoNaturezaDespesa(fieldSet.readInt("codigoNatureza"))
                        .descricaoNaturezaDespesa(fieldSet.readString("descricaoNatureza"))
                        .lancamentoAux(Lancamento.builder()
                                .descricaoLancamento(fieldSet.readString("descricaoLancamento"))
                                .dataLancamento(fieldSet.readDate("data"))
                                .valorLancamento(fieldSet.readBigDecimal("valor"))
                                .build())
                        .build();
            }
        };
    }
}
