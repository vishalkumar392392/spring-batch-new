package com.spring.batch.migration.databasemigration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.spring.batch.migration.databasemigration.mysql.dto.StudentMysqlDTO;
import com.spring.batch.migration.databasemigration.pg.dto.StudentDTO;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class DataMigrJobConfig {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager platformTransactionManager;

	@Autowired
	@Qualifier("postgresqlEntityManagerFactory")
	private EntityManagerFactory postgresEntityManagerFactory;

	@Autowired
	@Qualifier("mysqlEntityManagerFactory")
	private EntityManagerFactory mysqlEntityManagerFactory;

	@Autowired
	private DataItemProcessor dataItemProcessor;

	@Bean
	public Job dataJob(JobRepository jobRepository, JpaTransactionManager jpaTransactionManager) throws Exception {
		return new JobBuilder("dataJob", jobRepository).start(dataStep(jobRepository, jpaTransactionManager)).build();
	}

	@Bean
	public Step dataStep(JobRepository jobRepository, JpaTransactionManager jpaTransactionManager) throws Exception {

		return new StepBuilder("dataStep", jobRepository).<StudentDTO, StudentMysqlDTO>chunk(3, jpaTransactionManager)
				.reader(jpaCursorItemReader()).processor(dataItemProcessor).writer(jpaItemWriter()).build();
	}

	public JpaCursorItemReader<StudentDTO> jpaCursorItemReader() {
		JpaCursorItemReader<StudentDTO> jpaCursorItemReader = new JpaCursorItemReader<StudentDTO>();
		jpaCursorItemReader.setEntityManagerFactory(postgresEntityManagerFactory);
		jpaCursorItemReader.setQueryString("FROM StudentDTO");
		return jpaCursorItemReader;
	}

	public JpaItemWriter<StudentMysqlDTO> jpaItemWriter() {
		JpaItemWriter<StudentMysqlDTO> jpaItemWriter = new JpaItemWriter<StudentMysqlDTO>();
		jpaItemWriter.setEntityManagerFactory(mysqlEntityManagerFactory);
		return jpaItemWriter;
	}

}
