package com.spring.batch.migration.databasemigration.mysql.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.batch.migration.databasemigration.mysql.dto.StudentDto;

public interface StudentRepository extends JpaRepository<StudentDto, Integer> {

	@Query(value = "select * from student", nativeQuery = true)
	List<StudentDto> getAllStudents();

}
