package com.pathshala.services;

import org.springframework.stereotype.Service;

import com.pathshala.DTO.CourseInputDTO;
import com.pathshala.DTO.CourseResultDTO;

@Service
public interface CoursesService {
	
	public CourseResultDTO uploadCourse(CourseInputDTO course, String sessionID);
}
