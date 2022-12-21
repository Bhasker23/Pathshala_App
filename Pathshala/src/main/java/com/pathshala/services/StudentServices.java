package com.pathshala.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pathshala.DTO.AssignedCourseDTO;
import com.pathshala.DTO.StudentAdmissionInputDTO;
import com.pathshala.DTO.StudentAdmissionResultDTO;
import com.pathshala.DTO.StudentProfileUpdateInputDTO;
import com.pathshala.DTO.StudentProfileUpdateResultDTO;
import com.pathshala.models.Student;

@Service
public interface StudentServices {

	public StudentAdmissionResultDTO admitStudent(StudentAdmissionInputDTO stAdmit, String sessionId);
	
	public List<Student> findStudentByName(String sessionId, String stName);
	
	public String assignCourse(String sessionId, Integer courseId, Integer stId);
	
	public StudentProfileUpdateResultDTO updateProfile(Integer stCode, StudentProfileUpdateInputDTO stInput);

	public List<AssignedCourseDTO> getAllAssignedCourseDetails(Integer stid);
	
	public String leaveCourse(Integer stid,Integer cid);
}
