package com.pathshala.DTO;

import java.util.List;

import com.pathshala.models.StudentAddresses;

import lombok.Data;

@Data
public class StudentResDto {
	
	private	Integer studentId;
	private	String name;
	private String gender;
	private String dob;
	private String parentName;
	
	private List<StudentAddresses> address;

}
