package com.ikubinfo.suggestion;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan("com.ikubinfo.suggestion")
@EnableTransactionManagement
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Bean
	public DataSource datasource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/taxibook");
		dataSource.setUsername("postgres");
		dataSource.setPassword("1111");

		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(datasource());
	}



}