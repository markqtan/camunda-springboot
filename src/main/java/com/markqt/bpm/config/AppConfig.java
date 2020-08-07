package com.markqt.bpm.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@PropertySource("classpath:datasource-mysql.properties")
public class AppConfig {
	@Autowired
	private Environment env;
	
	@Bean
	DataSource getDataSource() {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setUrl(env.getProperty("spring.datasource.url"));
		ds.setUser(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		return ds;
	}
}
