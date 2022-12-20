package com.pathshala.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathshala.DTO.StudentAdmissionInputDTO;
import com.pathshala.DTO.StudentAdmissionResultDTO;
import com.pathshala.GlobalExceptionHandler.LoginException;
import com.pathshala.GlobalExceptionHandler.StudentException;
import com.pathshala.models.Student;
import com.pathshala.repo.CurrentSessionRepo;
import com.pathshala.repo.StudentRepo;

@Service
public class StudentServicesImpl implements StudentServices {

	@Autowired
	CurrentSessionRepo cRepo;
	
	@Autowired
	StudentRepo stRepo;
	
	
	@Override
	public StudentAdmissionResultDTO admitStudent(StudentAdmissionInputDTO stAdmit, String sessionId) {
		
		if(cRepo.findById(sessionId).isEmpty()) {
			throw new LoginException("Admin is not logged In , Please Login First !");
		}
		
		if(stRepo.findById(stAdmit.getStudentId()).isPresent()) {
			throw new StudentException(stAdmit.getStudentId()+" stundet code has been already Admitted in Pathshala");
		}
		
		Student st = new Student();
		st.setStudentId(stAdmit.getStudentId());
		st.setName(stAdmit.getName());;
		st.setGender(stAdmit.getGender());
		st.setDob(stAdmit.getDob());
		st.setParentName(stAdmit.getParentName());
//		st.setAddress(stAdmit.getAddress());
		
		stRepo.save(st);
		
		StudentAdmissionResultDTO stResult = new StudentAdmissionResultDTO();
		stResult.setStudentName("Welcome "+stAdmit.getName()+ " you got addmission in Pathshala. ");
		stResult.setStudentCode(stAdmit.getStudentId());
		
		
		
		
		return stResult;
	}

}
