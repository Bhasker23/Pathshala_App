package com.pathshala.models;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Course {
	
	@Id
	private Integer id;
	private String courseName;
	private String discription;
	private String courseType;
	private String duration;
	private String topics;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Set<Student> stuentList;
	
	
}
