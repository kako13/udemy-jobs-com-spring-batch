package com.controle.transacional.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.sql.XADataSource;

@Configuration
public class DatasourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource springDS() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "db1.datasource")
    public DataSourceProperties db1Props() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource db1AppDS(@Qualifier("db1Props") DataSourceProperties dsProps) {
        XADataSource xaDataSource = createXaDataSource(dsProps);
        AtomikosDataSourceBean bean = new AtomikosDataSourceBean();
        bean.setUniqueResourceName("xaDB1");
        bean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        bean.setLocalTransactionMode(true);
        bean.setPoolSize(3);
        bean.setXaDataSource(xaDataSource);
        return bean;
    }

    @Bean
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSourceProperties db2Props() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource db2AppDS(@Qualifier("db2Props") DataSourceProperties dsProps) {
        XADataSource xaDataSource = createXaDataSource(dsProps);
        AtomikosDataSourceBean bean = new AtomikosDataSourceBean();
        bean.setUniqueResourceName("xaDB2");
        bean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        bean.setLocalTransactionMode(true);
        bean.setPoolSize(3);
        bean.setXaDataSource(xaDataSource);
        return bean;
    }

    public XADataSource createXaDataSource(DataSourceProperties dsProps) {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(dsProps.getUrl());
        mysqlXADataSource.setUser(dsProps.getUsername());
        mysqlXADataSource.setPassword(dsProps.getPassword());
        return mysqlXADataSource;
    }

    public UserTransactionManager userTransactionManager() throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setTransactionTimeout(300);
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean
    public PlatformTransactionManager jtaTransactionManager() throws SystemException {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager()); //ref="userTransactionManager"
        jtaTransactionManager.setUserTransaction(new UserTransactionImp()); //ref="userTransactionManager"
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        return jtaTransactionManager;
    }
}