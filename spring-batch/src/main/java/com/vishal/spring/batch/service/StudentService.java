package com.vishal.spring.batch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vishal.spring.batch.modal.StudentJson;

@Service
public class StudentService {

	List<StudentJson> list = null;
	int incrementor = 0;

	public List<StudentJson> restCall() {

		System.out.println("restCall()");

		RestTemplate restTemplate = new RestTemplate();

		StudentJson[] students = restTemplate.getForObject("http://localhost:8080/students", StudentJson[].class);

		list = new ArrayList<>();

		for (StudentJson stu : students) {
			list.add(stu);
		}

		return list;
	}

	public StudentJson getStudent() {

		if (incrementor == 0 && list == null) {
			restCall();
		}
		if (list != null && incrementor < list.size()) {
			return list.get(incrementor++);
		}
		incrementor = 0;

		return null;

	}

	public StudentJson studentResponse(StudentJson studentJson) {

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject("http://localhost:8080/students", studentJson, StudentJson.class);
	}

}
