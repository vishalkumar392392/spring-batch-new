package com.spring.batch.migration.databasemigration.mysql.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class StudentMysqlDTO {
	
	@Id
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String email;
	@Column(name = "dept_id")
	private String deptId;
	@Column(name = "is_active")
	private Boolean isActive;

}
