package com.pathshala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pathshala.DTO.AssignedCourseDTO;
import com.pathshala.DTO.StudentProfileUpdateInputDTO;
import com.pathshala.DTO.StudentProfileUpdateResultDTO;
import com.pathshala.models.Course;
import com.pathshala.services.StudentServices;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentServices stServices;
	
	@PutMapping("/updateprofile/{stcode}")
	public ResponseEntity<StudentProfileUpdateResultDTO> updateProfile(@PathVariable Integer stcode, @RequestBody StudentProfileUpdateInputDTO stInput){
		
		return new ResponseEntity<StudentProfileUpdateResultDTO>(stServices.updateProfile(stcode, stInput),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getCourseDetails/{stId}")
	public ResponseEntity<List<AssignedCourseDTO>> getAllAssignedCourseDetails(@PathVariable Integer stId){
		return new ResponseEntity<>(stServices.getAllAssignedCourseDetails(stId),HttpStatus.OK);
	}
	
	@DeleteMapping("/leaveCourse/{stid}/{cid}")
	public ResponseEntity<String> leaveCourse(@PathVariable Integer stid,@PathVariable Integer cid){
		return new ResponseEntity<String>(stServices.leaveCourse(stid, cid),HttpStatus.OK);
	}
	
}
