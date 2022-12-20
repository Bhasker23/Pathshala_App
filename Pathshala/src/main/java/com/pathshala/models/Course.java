package com.pathshala.models;



import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String courseName;
	private String discription;
	private String courseType;
	private String duration;
	private String topics;
	@ManyToMany
	private Set<Student> stuentList;
	
	
}
