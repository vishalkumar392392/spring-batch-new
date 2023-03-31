package com.spring.batch.migration.databasemigration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = { "com.spring.batch.migration.databasemigration.pg.repo",
		"com.spring.batch.migration.databasemigration.pg.service.impl" }, entityManagerFactoryRef = "postgresqlEntityManagerFactory", transactionManagerRef = "postgreTransactionManager"

)
@Component
public class PostgresDbConfig {

	@Value("${spring.postgresdatasource.url}")
	private String url;

	@Value("${spring.postgresdatasource.username}")
	private String username;

	@Value("${spring.postgresdatasource.password}")
	private String password;

	@Value("${spring.postgresdatasource.driver-class-name}")
	private String driverClassName;

	@Bean(name = "postgresqlDataSource")
	public DataSource dataSource() {
		DataSourceBuilder<?> create = DataSourceBuilder.create();
		create.username("postgres");
		create.password("8143486643");
		create.url("jdbc:postgresql://localhost:5432/university");
		create.driverClassName("org.postgresql.Driver");
		return create.build();
	}

	@Bean(name = "postgresqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("postgresqlDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource)
				.packages(new String[] { "com.spring.batch.migration.databasemigration.pg.dto" })
				.persistenceUnit("postgresql").build();
	}

	@Bean(name = "postgreTransactionManager")
	public PlatformTransactionManager postgreTransactionManager(
			@Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
