package com.spring.batch.migration.databasemigration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.batch.migration.databasemigration.pg.dto.StudentDTO;
import com.spring.batch.migration.databasemigration.pg.service.PgStudentService;

@RestController
public class PgController {
	
	@Autowired
	private PgStudentService pgStudentService;
	
	@GetMapping("/pg/students")
	public List<StudentDTO> getStudents(){
		
		return pgStudentService.getAllStudents();
		
	}

}
