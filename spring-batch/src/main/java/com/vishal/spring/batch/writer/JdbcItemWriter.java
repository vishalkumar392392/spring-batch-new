package com.vishal.spring.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.vishal.spring.batch.modal.StudentJDBC;

@Component
public class JdbcItemWriter implements ItemWriter<StudentJDBC> {

	@Override
	public void write(List<? extends StudentJDBC> items) throws Exception {

		System.out.println("Inside JDBC ItemWriter");

		items.stream().forEach(System.out::println);
	}

}
