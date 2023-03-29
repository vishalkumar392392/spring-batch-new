package com.spring.batch.migration.databasemigration;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.spring.batch.migration.databasemigration.mysql.dto",
		"com.spring.batch.migration.databasemigration.mysql.repo",
		"com.spring.batch.migration.databasemigration.controller",
		"com.spring.batch.migration.databasemigration.service.impl",
		"com.spring.batch.migration.databasemigration.pg.dto", "com.spring.batch.migration.databasemigration.pg.repo",
		"com.spring.batch.migration.databasemigration.pg.service.impl", })
public class DatabaseMigrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseMigrationApplication.class, args);
	}

	@Bean(name = "postgresqlDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.postgres")
	public DataSource dataSource() {
		DataSourceBuilder<?> create = DataSourceBuilder.create();
		create.username("postgres");
		create.password("8143486643");
		create.url("jdbc:postgresql://localhost:5432/university");
		create.driverClassName("org.postgresql.Driver");
		return create.build();
	}

}
