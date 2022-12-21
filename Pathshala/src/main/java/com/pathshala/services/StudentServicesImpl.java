package com.pathshala.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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
		
		if(cRepo.findById(sessionId).isEmpty()) {
			throw new LoginException("Admin is not logged In , Please Login First !");
		}
		
		if (!stRepo.findByName(stName).contains(stName)) {
			throw new StudentException(stName + " is not taken addmission yet in Pathshala");
		}
//		
//		List<Student> list = new ArrayList<>(); 
//		Student st = 
//		list.add(st);
		
		return stRepo.findByName(stName);
	}

}
