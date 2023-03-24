package com.vishal.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vishal.spring.batch.processor.FirstItemprocessor;
import com.vishal.spring.batch.reader.FirstItemReader;
import com.vishal.spring.batch.writer.FirstItemWriter;

@Configuration
public class ChuckOrientedJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private FirstItemReader firstItemReader;

	@Autowired
	private FirstItemprocessor firstItemprocessor;

	@Autowired
	private FirstItemWriter firstItemWriter;

	@Bean
	public Job getJob() {

		return jobBuilderFactory.get("chuckJob").incrementer(new RunIdIncrementer()).start(firstChuckStep()).build();
	}

	private Step firstChuckStep() {
		return stepBuilderFactory.get("First Chunk Step")
								 .<Integer, Long>chunk(3)
								 .reader(firstItemReader)
								 .processor(firstItemprocessor)
								 .writer(firstItemWriter)
								 .build();
	}
}
