package com.pathshala.DTO;

import lombok.Data;

@Data
public class CourseInputDTO {
	
	private Integer id;
	private String courseName;
	private String discription;
	private String courseType;
	private String duration;
	private String topics;
}
