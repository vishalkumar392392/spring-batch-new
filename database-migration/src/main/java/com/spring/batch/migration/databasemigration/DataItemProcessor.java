package com.spring.batch.migration.databasemigration;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.spring.batch.migration.databasemigration.mysql.dto.StudentMysqlDTO;
import com.spring.batch.migration.databasemigration.pg.dto.StudentDTO;

@Component
public class DataItemProcessor implements ItemProcessor<StudentDTO, StudentMysqlDTO> {

	@Override
	public StudentMysqlDTO process(StudentDTO item) throws Exception {
		System.out.println("item" + item);

		StudentMysqlDTO studentMysqlDTO = new StudentMysqlDTO();
		studentMysqlDTO.setDeptId(item.getDeptId());
		studentMysqlDTO.setEmail(item.getEmail());
		studentMysqlDTO.setFirstName(item.getFirstName());
		studentMysqlDTO.setId(item.getId());
		studentMysqlDTO.setIsActive(item.getIsActive());
		studentMysqlDTO.setLastName(item.getLastName());

		return studentMysqlDTO;
	}

}
