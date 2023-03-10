package com.pathshala.controller;

import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pathshala.DTO.CourseInputDTO;
import com.pathshala.DTO.CourseResultDTO;
import com.pathshala.DTO.LoginCredDTO;
import com.pathshala.DTO.LoginResultDTO;
import com.pathshala.DTO.StudentAdmissionInputDTO;
import com.pathshala.DTO.StudentAdmissionResultDTO;
import com.pathshala.DTO.StudentResDto;
import com.pathshala.models.CurrentSession;
import com.pathshala.models.Student;
import com.pathshala.services.AdminLoginService;
import com.pathshala.services.CoursesService;
import com.pathshala.services.StudentServices;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminLoginService adminLogin;
	
	@Autowired
	CoursesService coursesService;
	
	@Autowired
	StudentServices stService;
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResultDTO> adminLogin(@RequestBody LoginCredDTO loginCred){
	
		return new ResponseEntity<LoginResultDTO> (adminLogin.loginAdmin(loginCred), HttpStatus.OK);
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> adminLogOut(@RequestParam String sessionId){
	
		return new ResponseEntity<String> (adminLogin.logOutAdmin(sessionId), HttpStatus.OK);
	}
	
	
	@PostMapping("/studentaddmission/{sessionId}")
	public ResponseEntity<StudentAdmissionResultDTO> admitStudent(@RequestBody StudentAdmissionInputDTO stAddmission , @PathVariable String sessionId){
		
		return new ResponseEntity<StudentAdmissionResultDTO>(stService.admitStudent(stAddmission, sessionId), HttpStatus.CREATED);
	}
	
	@PostMapping("/uploadcourse/{sessionId}")
	public ResponseEntity<CourseResultDTO> uploadCourse(@RequestBody CourseInputDTO course , @PathVariable String sessionId){
		
		return new ResponseEntity<CourseResultDTO> (coursesService.uploadCourse(course, sessionId), HttpStatus.CREATED);
	}
	
	@GetMapping("/searchstudent/{sessionId}")
	public ResponseEntity<List<Student>> findStudentByName(@PathVariable String sessionId, @RequestParam String stName){
		
		return new ResponseEntity<List<Student>>(stService.findStudentByName(sessionId, stName),HttpStatus.OK);
		
	}
	
	@PutMapping("/assigncourse/{sessionId}")
	public ResponseEntity<String> assignCourse(@PathVariable String sessionId, @RequestParam Integer courseId, @RequestParam Integer stId  ){
		
		return new ResponseEntity<String>(stService.assignCourse(sessionId, courseId, stId),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllStudentsOfACourse/{sessionId}/{cid}")
	public ResponseEntity<List<StudentResDto>> getAllStudentOfACourse(@PathVariable String sessionId,@PathVariable Integer cid){
		
		return new ResponseEntity<List<StudentResDto>>(adminLogin.getAllStudentOfACourse(sessionId, cid), HttpStatus.OK);
		
	}
	
	
}
