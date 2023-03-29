package com.spring.batch.migration.databasemigration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.batch.migration.databasemigration.mysql.dto.StudentDto;
import com.spring.batch.migration.databasemigration.mysql.repo.StudentRepository;
import com.spring.batch.migration.databasemigration.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<StudentDto> getStudents() {
		return studentRepository.getAllStudents();
	}

}
