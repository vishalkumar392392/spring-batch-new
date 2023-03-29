package com.spring.batch.migration.databasemigration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.spring.batch.migration.databasemigration.pg.repo" }, entityManagerFactoryRef = "postgresqlEntityManagerFactory", transactionManagerRef = "postgreTransactionManager"

)
@EnableTransactionManagement
public class PostgresDbConfig {

	@Bean(name = "postgresqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("postgresqlDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.spring.batch.migration.databasemigration.pg.dto").persistenceUnit("postgresql")
				.build();
	}

	@Bean(name = "postgreTransactionManager")
	public PlatformTransactionManager postgreTransactionManager(
			@Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
