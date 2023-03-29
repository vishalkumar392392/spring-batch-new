package com.spring.batch.migration.databasemigration.pg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.batch.migration.databasemigration.pg.dto.StudentDTO;
import com.spring.batch.migration.databasemigration.pg.repo.PgStudentRepository;
import com.spring.batch.migration.databasemigration.pg.service.PgStudentService;

@Service
public class PgStudentServiceImpl implements PgStudentService {
	
	@Autowired
	@Qualifier("pgStudentRepository")
	private PgStudentRepository studentRepository;

	@Override
	public List<StudentDTO> getAllStudents() {
		return studentRepository.getAllStudents();
	}

}
