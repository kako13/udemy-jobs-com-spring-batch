package com.springbatch.skipexception.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.skipexception.dominio.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class SkipExceptionReaderConfig {
	@Bean
	public ItemReader<Cliente> skipExceptionReader(@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Cliente>()
				.name("skipExceptionReader")
				.dataSource(dataSource)
				.sql("select * from cliente")
//				.beanRowMapper(Cliente.class)
				.rowMapper(rowMapper())
				.build();
	}

	private RowMapper<Cliente> rowMapper() {
		return new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				/*
				 Tenho 11 registros no banco e uma tolerância de skip de 2
				 utilizar '<= 11 para testar o skip com erro
				 ou '==' 11 para testar o skip sem erro
				*/
				if (rs.getRow() == 11) {
					throw new SQLException(String.format("Encerrando a execução!! Cliente %s inválido", rs.getRow()));
				} else
					return clienteRowMapper(rs);
			}
		};
	}

	private Cliente clienteRowMapper(ResultSet rs) throws SQLException {
		return Cliente.builder()
				.email(rs.getString("email"))
				.nome(rs.getString("nome"))
				.sobrenome(rs.getString("sobrenome"))
				.idade(rs.getString("idade"))
				.build();
	}
}
