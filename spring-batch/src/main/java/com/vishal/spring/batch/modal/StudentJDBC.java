package com.vishal.spring.batch.modal;

import lombok.Data;

@Data
public class StudentJDBC {

	private Long id;
	private String firstName;
	private String lastName;
	private String email; 
}