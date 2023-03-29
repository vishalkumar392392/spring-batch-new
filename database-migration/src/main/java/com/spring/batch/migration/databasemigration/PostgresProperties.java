package com.spring.batch.migration.databasemigration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties("spring.postgresdatasource")
@PropertySource(value = { "classpath:application.properties" })
public class PostgresProperties {

	private String url;
	private String username;
	private String password;

}
