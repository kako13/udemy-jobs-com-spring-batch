package com.springbatch.demonstrativoorcamento.reader;

import com.springbatch.demonstrativoorcamento.dominio.GrupoLancamento;
import com.springbatch.demonstrativoorcamento.dominio.Lancamento;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class BancoLancamentoItemReader {

    @Bean
    public JdbcCursorItemReader<GrupoLancamento> dataBaseItemReader(@Qualifier("appDataSource") DataSource dataSource) {

        System.out.println(">>>>>>> Lendo do Banco de dados! <<<<<<<");

        return new JdbcCursorItemReaderBuilder<GrupoLancamento>()
                .name("dataBaseItemReader")
                .dataSource(dataSource)
                .sql("select * from lancamento")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<GrupoLancamento> rowMapper() {
        return new RowMapper<GrupoLancamento>() {
            @Override
            public GrupoLancamento mapRow(ResultSet rs, int rowNum) throws SQLException {
                return GrupoLancamento.builder()
                        .codigoNaturezaDespesa(rs.getInt("codigoNaturezaDespesa"))
                        .descricaoNaturezaDespesa(rs.getString("descricaoNaturezaDespesa"))
                        .lancamentoAux(Lancamento.builder()
                                                .descricaoLancamento(rs.getString("descricaoLancamento"))
                                                .dataLancamento(rs.getDate("dataLancamento"))
                                                .valorLancamento(rs.getBigDecimal("valorLancamento"))
                                                .build())
                        .build();
            }
        };
    }
}
