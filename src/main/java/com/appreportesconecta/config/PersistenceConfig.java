package com.appreportesconecta.config;

import java.beans.PropertyVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class PersistenceConfig {

	@Value("${driver}")
	String driver;

	@Value("${jdbcUrl}")
	String jdbcUrl;

	@Value("${userdb}")
	String username;

	@Value("${password}")
	String password;

	@Value("${model}")
	String model;

	@Value("${showSql}")
	String showSql;

	@Value("${dialect}")
	String dialect;

	@Value("${minPool}")
	int minPool;

	@Value("${maxPool}")
	int maxPool;

	@Value("${maxIddleTime}")
	int maxIddleTime;

	@Value("${acquireIncrement}")
	int acquireIncrement;

	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {

		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass(this.driver);
		ds.setJdbcUrl(this.jdbcUrl);
		ds.setUser(this.username);
		ds.setPassword(this.password);
		ds.setAcquireIncrement(this.acquireIncrement);
		ds.setMinPoolSize(this.minPool);
		ds.setMaxPoolSize(this.maxPool);
		ds.setMaxIdleTime(this.maxIddleTime);

		return ds;
	}

}
