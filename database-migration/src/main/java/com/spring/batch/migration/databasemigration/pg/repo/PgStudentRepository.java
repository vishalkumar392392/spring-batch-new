package com.spring.batch.migration.databasemigration.pg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.batch.migration.databasemigration.pg.dto.StudentDTO;

public interface PgStudentRepository extends JpaRepository<StudentDTO, Integer> {

	@Query(value = "select * from student", nativeQuery = true)
	List<StudentDTO> getAllStudents();

}
