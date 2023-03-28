package com.vishal.spring.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableAsync
@EnableScheduling
public class SpringBatchIntroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchIntroApplication.class, args);
	}
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.seconddatasource")
	public DataSource seconddatasource() {
		return DataSourceBuilder.create().build();
	}

}
