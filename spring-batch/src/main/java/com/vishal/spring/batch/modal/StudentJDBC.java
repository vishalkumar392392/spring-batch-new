package com.vishal.spring.batch.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentJDBC {

	private Long id;
	private String firstName;
	private String lastName;
	private String email; 
}
