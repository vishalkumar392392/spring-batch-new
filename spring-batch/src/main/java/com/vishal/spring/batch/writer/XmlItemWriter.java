package com.vishal.spring.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.vishal.spring.batch.modal.StudentXml;

@Component
public class XmlItemWriter implements ItemWriter<StudentXml> {

	@Override
	public void write(List<? extends StudentXml> items) throws Exception {

		System.out.println("Inside XML Item Writer");

		items.stream().forEach(System.out::println);
	}

}
