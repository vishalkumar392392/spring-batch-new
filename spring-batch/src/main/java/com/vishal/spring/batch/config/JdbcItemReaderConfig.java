package com.vishal.spring.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.vishal.spring.batch.modal.StudentJDBC;
import com.vishal.spring.batch.writer.JdbcItemWriter;

@Configuration
public class JdbcItemReaderConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JdbcItemWriter jdbcItemWriter;

	@Autowired
	private DataSource dataSource;

	@Bean
	public Job jdbcJob() {

		return jobBuilderFactory.get("jdbcJob").start(jdbcStep()).build();
	}

	private Step jdbcStep() {
		return stepBuilderFactory.get("jdbcStep").<StudentJDBC, StudentJDBC>chunk(3).reader(jdbcItemReader())
				.writer(jdbcItemWriter).build();
	}

	private JdbcCursorItemReader<StudentJDBC> jdbcItemReader() {

		JdbcCursorItemReader<StudentJDBC> jdbcCursorItemReader = new JdbcCursorItemReader<>();

		jdbcCursorItemReader.setDataSource(dataSource);
		jdbcCursorItemReader.setSql("SELECT * FROM student");
		jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<StudentJDBC>() {
			{
				setMappedClass(StudentJDBC.class);
			}
		});

		return jdbcCursorItemReader;
	}

}
