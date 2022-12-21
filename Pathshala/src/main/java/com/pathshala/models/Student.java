package com.pathshala.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
	private	Integer studentId;
	private	String name;
	private String gender;
	private String dob;
	private String parentName;
	@OneToMany(cascade = CascadeType.ALL)
	private List<StudentAddresses> address;
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Course> course;
	
	
}
