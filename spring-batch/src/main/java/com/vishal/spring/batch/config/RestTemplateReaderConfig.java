package com.vishal.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vishal.spring.batch.modal.StudentJson;
import com.vishal.spring.batch.service.StudentService;
import com.vishal.spring.batch.writer.JsonItemWriter;

@Configuration
public class RestTemplateReaderConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JsonItemWriter jsonItemWriter;

	@Autowired
	private StudentService studentService;

	@Bean
	public Job restTemplateJob() {

		return jobBuilderFactory.get("restTemplateJob").start(restTemplateStep()).build();
	}

	private Step restTemplateStep() {
		return stepBuilderFactory.get("restTemplateStep").<StudentJson, StudentJson>chunk(3).reader(itemReaderAdapter())
				.writer(jsonItemWriter).build();
	}

	private ItemReaderAdapter<StudentJson> itemReaderAdapter() {

		ItemReaderAdapter<StudentJson> itemReaderAdapter = new ItemReaderAdapter<StudentJson>();
		itemReaderAdapter.setTargetObject(studentService);
		itemReaderAdapter.setTargetMethod("getStudent");

		return itemReaderAdapter;
	}

}
