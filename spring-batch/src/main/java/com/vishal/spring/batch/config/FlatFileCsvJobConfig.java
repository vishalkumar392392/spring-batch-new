package com.vishal.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.vishal.spring.batch.modal.StudentCSV;
import com.vishal.spring.batch.writer.FlatFileCsvItemWriter;

@Configuration
public class FlatFileCsvJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private FlatFileCsvItemWriter flatFileCsvItemWriter;

	@Bean
	public Job csvJob() {

		return jobBuilderFactory.get("csvJob").start(csvStep()).build();
	}

	private Step csvStep() {
		return stepBuilderFactory.get("csvStep").<StudentCSV, StudentCSV>chunk(3).reader(flatFileItemReader())
				.writer(flatFileCsvItemWriter).build();
	}

	public FlatFileItemReader<StudentCSV> flatFileItemReader() {
		FlatFileItemReader<StudentCSV> flatFileItemreader = new FlatFileItemReader<StudentCSV>();

		flatFileItemreader.setResource(new ClassPathResource("students.csv"));
		/**
		 * flatFileItemreader.setLineMapper(new DefaultLineMapper<>() { {
		 * setLineTokenizer(new DelimitedLineTokenizer() { { setNames("Id", "First
		 * Name", "Last Name", "Email"); } });
		 * 
		 * setFieldSetMapper(new BeanWrapperFieldSetMapper<>() { {
		 * setTargetType(StudentCSV.class); } }); } });
		 * flatFileItemreader.setLinesToSkip(1);
		 */

		DefaultLineMapper<StudentCSV> defaultLineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames("Id", "First Name", "Last Name", "Email");
		BeanWrapperFieldSetMapper<StudentCSV> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(StudentCSV.class);

		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

		flatFileItemreader.setLineMapper(defaultLineMapper);
		flatFileItemreader.setLinesToSkip(1);
		return flatFileItemreader;
	}

}
