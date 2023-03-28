package com.vishal.spring.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.vishal.spring.batch.modal.StudentCSV;

@Component
public class FlatFileCsvItemWriter implements ItemWriter<StudentCSV> {

	@Override
	public void write(List<? extends StudentCSV> items) throws Exception {

		System.out.println("Inside FlatFileCsvItemWriter");

		items.stream().forEach(System.out::println);

	}

}
