package com.vishal.spring.batch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vishal.spring.batch.modal.StudentJson;

@Service
public class StudentService {

	List<StudentJson> list = null;

	public List<StudentJson> restCall() {

		RestTemplate restTemplate = new RestTemplate();

		StudentJson[] students = restTemplate.getForObject("http://localhost:8081/students", StudentJson[].class);

		list = new ArrayList<>();

		for (StudentJson stu : students) {
			list.add(stu);
		}

		return list;
	}

	public StudentJson getStudent() {

		if (list == null) {
			restCall();
		}

		if (list != null && !list.isEmpty()) {
			return list.remove(0);
		}

		return null;

	}

}
