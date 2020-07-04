package com.javamsdt.clientdeal.model.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"com.javamsdt.clientdeal"})
public class AppConfig {

	private static final Logger LOGGER = LogManager.getLogger();

	@Bean("dataSource")
	public DataSource getDataSource() {

		HikariConfig config = new HikariConfig();
		config.setUsername("postgres");
		config.setPassword("root");
		config.setJdbcUrl("jdbc:postgresql://localhost:5432/client_deal");
		config.setDriverClassName("org.postgresql.Driver");

		LOGGER.info("DataSource Config From Hikari file: " + config.getCatalog() + ", " + config.getJdbcUrl());
		return new HikariDataSource(config);
	}

	@Bean("JdbcTemplate")
	public JdbcTemplate getJdbcTemplate(final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean("NamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(final DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean("transactionManager")
	public DataSourceTransactionManager transactionManager(final DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
