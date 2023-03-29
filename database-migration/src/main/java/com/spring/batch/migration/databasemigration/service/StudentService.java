package com.spring.batch.migration.databasemigration.service;

import java.util.List;

import com.spring.batch.migration.databasemigration.mysql.dto.StudentDto;

public interface StudentService {
	
	
	List<StudentDto> getStudents();

}
