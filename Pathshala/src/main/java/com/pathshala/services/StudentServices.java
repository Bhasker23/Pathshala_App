package com.pathshala.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pathshala.DTO.StudentAdmissionInputDTO;
import com.pathshala.DTO.StudentAdmissionResultDTO;
import com.pathshala.models.Student;

@Service
public interface StudentServices {

	public StudentAdmissionResultDTO admitStudent(StudentAdmissionInputDTO stAdmit, String sessionId);
	
	public List<Student> findStudentByName(String sessionId, String stName);
}
