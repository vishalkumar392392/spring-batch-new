package com.vishal.spring.batch.modal;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "student")
public class StudentXml {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;

}
