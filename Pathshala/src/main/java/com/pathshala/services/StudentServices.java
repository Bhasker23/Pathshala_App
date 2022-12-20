package com.pathshala.services;

import org.springframework.stereotype.Service;

import com.pathshala.DTO.StudentAdmissionInputDTO;
import com.pathshala.DTO.StudentAdmissionResultDTO;

@Service
public interface StudentServices {

	public StudentAdmissionResultDTO admitStudent(StudentAdmissionInputDTO stAdmit, String sessionId);
}
