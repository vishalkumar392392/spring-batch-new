package com.vishal.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vishal.spring.batch.listener.FirstJobListener;
import com.vishal.spring.batch.listener.FirstStepListener;

@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job firstJob(@Autowired FirstJobListener firstJobListener, @Autowired FirstStepListener firstStepListener) {
		return jobBuilderFactory.get("firstJob").incrementer(new RunIdIncrementer()).start(firstStep(firstStepListener))
				.next(secondStep()).listener(firstJobListener).build();
	}

	private Step firstStep(FirstStepListener firstStepListener) {
		return stepBuilderFactory.get("firstStep").tasklet(task()).listener(firstStepListener).build();
	}

	private Step secondStep() {
		return stepBuilderFactory.get("secondStep").tasklet(secondTask()).build();
	}

	private Tasklet task() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("First Tasklet job");
				return RepeatStatus.FINISHED;
			}
		};
	}

	private Tasklet secondTask() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Second Tasklet job");
				return RepeatStatus.FINISHED;
			}
		};
	}
}
