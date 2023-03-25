package com.vishal.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.vishal.spring.batch.modal.StudentXml;
import com.vishal.spring.batch.writer.XmlItemWriter;

@Configuration
public class XmlItemReaderConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private XmlItemWriter xmlItemWriter;

	@Bean
	public Job xmlJob() {

		return jobBuilderFactory.get("xmlJob").start(jsonStep()).build();
	}

	private Step jsonStep() {
		return stepBuilderFactory.get("xmlStep").<StudentXml, StudentXml>chunk(3).reader(xmlItemReader())
				.writer(xmlItemWriter).build();
	}

	private StaxEventItemReader<StudentXml> xmlItemReader() {

		StaxEventItemReader<StudentXml> staxEventItemReader = new StaxEventItemReader<>();
		staxEventItemReader.setResource(new ClassPathResource("students.xml"));
		staxEventItemReader.setFragmentRootElementName("student");
		staxEventItemReader.setUnmarshaller(new Jaxb2Marshaller() {
			{
				setClassesToBeBound(StudentXml.class);
			}
		});

		return staxEventItemReader;

	}

}
