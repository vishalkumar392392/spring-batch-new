package com.vishal.spring.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.vishal.spring.batch.modal.StudentJson;

@Component
public class JsonItemWriter implements ItemWriter<StudentJson> {

	@Override
	public void write(List<? extends StudentJson> items) throws Exception {

		System.out.println("Inside Item Writer");

		items.stream().forEach(System.out::println);

	}

}
