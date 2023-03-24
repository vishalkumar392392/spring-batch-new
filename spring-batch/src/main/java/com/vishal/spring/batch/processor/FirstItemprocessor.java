package com.vishal.spring.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemprocessor implements ItemProcessor<Integer, Long> {

	@Override
	public Long process(Integer item) throws Exception {
		System.out.println("Inside ItemProcessor");
		return Long.valueOf(item + 20);
	}

}
