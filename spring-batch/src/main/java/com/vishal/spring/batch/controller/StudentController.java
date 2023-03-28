package com.vishal.spring.batch.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.spring.batch.modal.StudentJson;

@RestController
public class StudentController {

	@GetMapping("/students")
	public List<StudentJson> getStudents() {

		return Arrays.asList(new StudentJson(1L, "vishal", "palla", "vishal@gmail"),
				new StudentJson(2L, "vikas", "palla", "vikas@gmail"),
				new StudentJson(3L, "ajith", "palla", "ajith@gmail"), new StudentJson(4L, "sai", "palla", "sai@gmail"),
				new StudentJson(5L, "susmitha", "palla", "susmitha@gmail"),
				new StudentJson(6L, "kavya", "palla", "kavya@gmail"),
				new StudentJson(7L, "chinnari", "palla", "chinnari@gmail"),
				new StudentJson(8L, "ramya", "palla", "ramya@gmail"),
				new StudentJson(9L, "gayathri", "palla", "gayathri@gmail"));

	}

	@PostMapping("/students")
	public StudentJson saveStudents(@RequestBody StudentJson studentJson) {
		return studentJson;

	}

}
