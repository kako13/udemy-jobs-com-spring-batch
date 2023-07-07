package com.springbatch.jdbcpagingreader.reader;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.jdbcpagingreader.dominio.Cliente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcPagingReaderReaderConfig {
	@Bean
	public JdbcPagingItemReader<Cliente> jdbcPagingReader(@Qualifier("myAppDataSource") DataSource myAppDataSource,
														  PagingQueryProvider queryProvider) {
		return new JdbcPagingItemReaderBuilder<Cliente>()
				.name("jdbcPagingReader")
				.dataSource(myAppDataSource)
				.queryProvider(queryProvider)
				.pageSize(1)
				.rowMapper(new BeanPropertyRowMapper<>(Cliente.class))
				.build();
	}


	@Bean
	public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier("myAppDataSource") DataSource myAppDataSource) {
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(myAppDataSource);
		queryProvider.setSelectClause("SELECT *");
		queryProvider.setFromClause("FROM cliente");
		queryProvider.setSortKey("email");
		return queryProvider;
	}
}
