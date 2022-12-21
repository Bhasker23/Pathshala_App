package com.pathshala.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathshala.DTO.LoginCredDTO;
import com.pathshala.DTO.LoginResultDTO;
import com.pathshala.DTO.StudentResDto;
import com.pathshala.GlobalExceptionHandler.CoursesException;
import com.pathshala.GlobalExceptionHandler.LoginException;
import com.pathshala.models.Course;
import com.pathshala.models.CurrentSession;
import com.pathshala.models.Student;
import com.pathshala.repo.AdminRepo;
import com.pathshala.repo.CourseRepo;
import com.pathshala.repo.CurrentSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	CurrentSessionRepo cs;
	
	@Autowired
	CourseRepo cRepo;
	
	@Override
	public LoginResultDTO loginAdmin(LoginCredDTO loginCred) {
		
		if(adminRepo.findById(loginCred.getUserName()).isEmpty()) {
			throw new LoginException("Please Enter Valid Credential");
		}
		
		if(cs.findByUserName(loginCred.getUserName()) != null) {
			throw new LoginException( loginCred.getUserName()+" is already login");
		}
		
		String uniqueID = RandomString.make(5);
		
		LoginResultDTO loginRes = new LoginResultDTO();
		
		loginRes.setSessionID(uniqueID);
		loginRes.setMessage("You are logged in");
		
		CurrentSession crSession = new CurrentSession();
		crSession.setSessionId(uniqueID);
		crSession.setUserName(loginCred.getUserName());
		cs.save(crSession);
		
		return loginRes;
	}

	@Override
	public String logOutAdmin(String sessionId) {
		
		
		if(cs.findById(sessionId).isEmpty()) {
			throw new LoginException(" You are not logged In");
		}		
		
		cs.deleteById(sessionId);
		
		return "logged out Successfully";
	}

	@Override
	public List<StudentResDto> getAllStudentOfACourse(String sessionid, Integer cid) {
	
		if(cs.findById(sessionid).isEmpty()) {
			throw new LoginException(" You are not logged In");
		}
		
       Optional<Course> course  = cRepo.findById(cid);
		
		if(course.isEmpty()) {
			throw new CoursesException( cid + "is not available in Pathshala yet");
		}
		
		ModelMapper modelmapper = new ModelMapper();
		List<Student> students  = course.get().getStuentList();
		List<StudentResDto> studentResDtos  = new ArrayList<>();
		
		for(Student student: students) {
			studentResDtos.add(modelmapper.map(student,StudentResDto.class));
		}
		
		return studentResDtos;
	}

}
