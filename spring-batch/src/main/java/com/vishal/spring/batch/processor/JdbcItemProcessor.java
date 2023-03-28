package com.vishal.spring.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.vishal.spring.batch.modal.StudentJDBC;

@Component
public class JdbcItemProcessor implements ItemProcessor<StudentJDBC, StudentJDBC> {

	@Override
	public StudentJDBC process(StudentJDBC item) throws Exception {
		// We intentionally made firstName as null for a record in database
		// We are doing item.getFirstName().length() to see whether our faultTolarence is working or not
		System.out.println(item.getFirstName().length());
		return item;
	}

}
