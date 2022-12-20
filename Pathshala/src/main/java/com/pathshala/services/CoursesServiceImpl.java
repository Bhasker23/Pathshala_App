package com.pathshala.services;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathshala.DTO.CourseInputDTO;
import com.pathshala.DTO.CourseResultDTO;
import com.pathshala.GlobalExceptionHandler.CoursesException;
import com.pathshala.GlobalExceptionHandler.LoginException;
import com.pathshala.models.Course;
import com.pathshala.models.CurrentSession;
import com.pathshala.repo.AdminRepo;
import com.pathshala.repo.CourseRepo;
import com.pathshala.repo.CurrentSessionRepo;

@Service
public class CoursesServiceImpl implements CoursesService {
	
	
	@Autowired
	CurrentSessionRepo cRepo;
	
	@Autowired
	CourseRepo courseRepo;
	
	@Override
	public CourseResultDTO uploadCourse(CourseInputDTO course, String sessionID) {
		
		if(cRepo.findById(sessionID).isEmpty()) {
			throw new LoginException("You are not logged In");
		}
			
		if(courseRepo.findById(course.getId()).isPresent()){
			throw new CoursesException(course.getCourseName()+" course has already been uploaded");
		}
		
		Course c = new Course();
		c.setId(course.getId());
		c.setCourseName(course.getCourseName());
		c.setCourseType(course.getCourseType());
		c.setDiscription(course.getDiscription());
		c.setDuration(course.getDuration());
		c.setTopics(course.getTopics());
		
		courseRepo.save(c);
		
		CourseResultDTO cResultDTO = new CourseResultDTO();
		cResultDTO.setMessage(c.getCourseName()+ " course has been uploaded Succuessfully ");
		
		return cResultDTO;
	}

}
