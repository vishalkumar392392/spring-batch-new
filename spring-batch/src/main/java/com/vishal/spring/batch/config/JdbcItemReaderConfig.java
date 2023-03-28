package com.vishal.spring.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.vishal.spring.batch.listener.SkipListener;
import com.vishal.spring.batch.modal.StudentJDBC;
import com.vishal.spring.batch.processor.JdbcItemProcessor;

@Configuration
public class JdbcItemReaderConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

//	@Autowired
//	private JdbcItemWriter jdbcItemWriter;

	@Autowired
	private JdbcItemProcessor jdbcItemProcessor;
	
	@Autowired
	private SkipListener skipListener;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Autowired
	@Qualifier("seconddatasource")
	private DataSource universityDataSource;

	@Bean
	public Job jdbcJob() {

		return jobBuilderFactory.get("jdbcJob").start(jdbcStep()).build();
	}

	private Step jdbcStep() {
		return stepBuilderFactory.get("jdbcStep").<StudentJDBC, StudentJDBC>chunk(3)
				.reader(jdbcItemReader())
				.processor(jdbcItemProcessor)
				.writer(jdbcBatchItemWriter())
				.faultTolerant()
				.skip(Throwable.class)
				.skipLimit(100)
				.retryLimit(1)
				.retry(Throwable.class)
				.listener(skipListener)
//				.skip(NullPointerException.class)
//				.skipPolicy(new AlwaysSkipItemSkipPolicy())
				.build();
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

	@Bean
	public JdbcBatchItemWriter<StudentJDBC> jdbcBatchItemWriter() {

		JdbcBatchItemWriter<StudentJDBC> jdbcBatchItemWriter = new JdbcBatchItemWriter<StudentJDBC>();
		jdbcBatchItemWriter.setDataSource(universityDataSource);
		jdbcBatchItemWriter.setSql("insert into student(firstName,lastName,email) values(:firstName,:lastName,:email)");

		jdbcBatchItemWriter
				.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<StudentJDBC>());
		return jdbcBatchItemWriter;
	}

}
