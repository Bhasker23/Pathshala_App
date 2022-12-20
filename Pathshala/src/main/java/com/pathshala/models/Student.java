package com.pathshala.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private	Integer studentId;
	private	String name;
	private String gender;
	private String dob;
	private String parentName;
	@OneToMany
	private List<StudentAddresses> address;
	@ManyToMany
	private Set<Course> course;
	
	
}
