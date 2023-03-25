package com.vishal.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.vishal.spring.batch.modal.StudentJson;
import com.vishal.spring.batch.writer.JsonItemWriter;

@Configuration
public class JsonItemReaderConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JsonItemWriter jsonItemWriter;

	@Bean
	public Job jsonJob() {

		return jobBuilderFactory.get("jsonJob").start(jsonStep()).build();
	}

	private Step jsonStep() {
		return stepBuilderFactory.get("jsonStep").<StudentJson, StudentJson>chunk(3).reader(jsonItemReader())
				.writer(jsonItemWriter).build();
	}

	private JsonItemReader<StudentJson> jsonItemReader() {

		JsonItemReader<StudentJson> jsonItemReader = new JsonItemReader<>();
		jsonItemReader.setResource(new ClassPathResource("students.json"));
		
		jsonItemReader.setJsonObjectReader(new JacksonJsonObjectReader<>(StudentJson.class));
		
//		jsonItemReader.setCurrentItemCount(2); //starts picking the 3rd element. 
//		jsonItemReader.setMaxItemCount(8); // Total it picks only 8 elements
		
		return jsonItemReader;
	}

}
