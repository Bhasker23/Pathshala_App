package com.pathshala.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathshala.DTO.StudentAdmissionInputDTO;
import com.pathshala.DTO.StudentAdmissionResultDTO;
import com.pathshala.GlobalExceptionHandler.CoursesException;
import com.pathshala.GlobalExceptionHandler.LoginException;
import com.pathshala.GlobalExceptionHandler.StudentException;
import com.pathshala.models.Course;
import com.pathshala.models.Student;
import com.pathshala.repo.CourseRepo;
import com.pathshala.repo.CurrentSessionRepo;
import com.pathshala.repo.StudentRepo;

@Service
public class StudentServicesImpl implements StudentServices {

	@Autowired
	CurrentSessionRepo cRepo;
	
	@Autowired
	StudentRepo stRepo;
	
	@Autowired
	CourseRepo courseRepo;
	
	
	@Override
	public StudentAdmissionResultDTO admitStudent(StudentAdmissionInputDTO stAdmit, String sessionId) {
		
		if(cRepo.findById(sessionId).isEmpty()) {
			throw new LoginException("Admin is not logged In , Please Login First !");
		}
		
		if(stRepo.findById(stAdmit.getStudentId()).isPresent()) {
			throw new StudentException(stAdmit.getStudentId()+" stundet code has been already Admitted in Pathshala");
		}
//		
//		Student st = new Student();
//		st.setStudentId(stAdmit.getStudentId());
//		st.setName(stAdmit.getName());
//		st.setGender(stAdmit.getGender());
//		st.setDob(stAdmit.getDob());
//		st.setParentName(stAdmit.getParentName());
//		st.setAddress(stAdmit.getAddress());
		
		ModelMapper mapper = new ModelMapper();
//		
		Student st = mapper.map(stAdmit, Student.class);
		
		stRepo.save(st);
		
		StudentAdmissionResultDTO stResult = new StudentAdmissionResultDTO();
		stResult.setStudentName("Welcome "+stAdmit.getName()+ " you got addmission in Pathshala. ");
		stResult.setStudentCode(stAdmit.getStudentId());
		
		
		
		
		return stResult;
	}


	@Override
	public List<Student> findStudentByName(String sessionId, String stName) {
		
		List<Student> list = new ArrayList<>();
		list = stRepo.findByName(stName);
		
		if(cRepo.findById(sessionId).isEmpty()) {
			throw new LoginException("Admin is not logged In , Please Login First !");
		}
		
		if(list.isEmpty()) {
			throw new StudentException( stName + " is not taken addmission yet in pathshala. ");
		}
		
		
		
		return list;
	}


	@Override
	public String assignCourse(String sessionId, Integer courseId, Integer stId) {
		
		if(cRepo.findById(sessionId).isEmpty()) {
			throw new LoginException("Admin is not logged In , Please Login First !");
		}
		
		if(stRepo.findById(stId).isEmpty()) {
			throw new StudentException(stId + "student code is not availble in Pathshala");
		}
		
		if(courseRepo.findById(courseId).isEmpty()) {
			throw new CoursesException( courseId + "is not available in Pathshala yet");
		}
		
		
		Set<Course> stCourses = new HashSet<>();
		stCourses.add(courseRepo.findById(courseId).get());
	    stRepo.findById(stId).get().setCourse(stCourses);
	    
	    
	    Set<Student> stStudent = new HashSet<>();
	    stStudent.add(stRepo.findById(stId).get());
	    courseRepo.findById(courseId).get().setStuentList(stStudent);
	    
	    
//		ModelMapper mapper = new ModelMapper();
//		Student st = mapper.map(stCourses, Student.class);
		
//        stRepo.save(st);		
		
		return stRepo.findById(stId).get().getName() + " has alloted " 
					+ courseRepo.findById(courseId).get().getCourseName()+ " Course.";
	}

}
