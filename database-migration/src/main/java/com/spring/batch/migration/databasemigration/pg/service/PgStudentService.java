package com.spring.batch.migration.databasemigration.pg.service;

import java.util.List;

import com.spring.batch.migration.databasemigration.pg.dto.StudentDTO;

public interface PgStudentService {
	
	List<StudentDTO> getAllStudents();


}
