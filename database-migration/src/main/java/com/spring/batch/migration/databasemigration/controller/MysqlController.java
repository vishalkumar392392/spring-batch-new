package com.spring.batch.migration.databasemigration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.batch.migration.databasemigration.mysql.dto.StudentDto;
import com.spring.batch.migration.databasemigration.service.StudentService;

@RestController
public class MysqlController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public List<StudentDto> getStudents() {
		return studentService.getStudents();
	}

}
