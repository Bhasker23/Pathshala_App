package com.pathshala.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathshala.DTO.AssignedCourseDTO;
import com.pathshala.DTO.StudentAdmissionInputDTO;
import com.pathshala.DTO.StudentAdmissionResultDTO;
import com.pathshala.DTO.StudentProfileUpdateInputDTO;
import com.pathshala.DTO.StudentProfileUpdateResultDTO;
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
		
		
		Student st = stRepo.findById(stId).get();
		Course c = courseRepo.findById(courseId).get();
		c.getStuentList().add(st);
		st.getCourse().add(courseRepo.findById(courseId).get());
		
		stRepo.save(st);
		
	    
//		ModelMapper mapper = new ModelMapper();
//		Student st = mapper.map(stCourses, Student.class);
		
//        stRepo.save(st);		
		
		return stRepo.findById(stId).get().getName() + " has alloted " 
					+ courseRepo.findById(courseId).get().getCourseName()+ " Course.";
	}


	@Override
	public StudentProfileUpdateResultDTO updateProfile(Integer stCode, StudentProfileUpdateInputDTO stInput) {
		
		if(stRepo.findById(stCode).isEmpty()) {
			throw new StudentException(stCode + "student code is not availble in Pathshala");
		}
		
		if(stRepo.findById(stCode).isPresent()) {
			
//			Student st = stRepo.findById(stCode).get();
//			st.setName(stInput.getName());
			
			ModelMapper mapper = new ModelMapper();
			Student st = mapper.map(stInput, Student.class);
			st.setStudentId(stCode);
			
			stRepo.save(st);
			
			
		}
		
		
		StudentProfileUpdateResultDTO stResult = new StudentProfileUpdateResultDTO();
		stResult.setMessage("Your Profile has been Updated");
		
		return stResult;
	}


	@Override
	public List<AssignedCourseDTO> getAllAssignedCourseDetails(Integer stid) {
		
		Optional<Student> st =  stRepo.findById(stid);
		
		if(stRepo.findById(stid).isEmpty()) {
			throw new StudentException(stid + "student code is not availble in Pathshala");
		}
			ModelMapper modelMapper = new ModelMapper();
			List<Course> courses = st.get().getCourse();
			
			List<AssignedCourseDTO> courseDTOs = new ArrayList<>();
			
			for(Course course:courses) {
				courseDTOs.add(modelMapper.map(course,AssignedCourseDTO.class));
			}
		
		return courseDTOs;
	}


	@Override
	public String leaveCourse(Integer stid,Integer cid) {
		
      Optional<Student> st =  stRepo.findById(stid);
		
		if(stRepo.findById(stid).isEmpty()) {
			throw new StudentException(stid + "student code is not availble in Pathshala");
		}
		
		
		Optional<Course> course  = courseRepo.findById(cid);
		
		if(course.isEmpty()) {
			throw new CoursesException( cid + "is not available in Pathshala yet");
		}
		
		course.get().getStuentList().remove(st.get());
		st.get().getCourse().remove(course.get());
		
		stRepo.save(st.get());
		
		return " Succesfully Exit From "+course.get().getCourseName() +" course";
		
	}

}
