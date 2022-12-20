package com.pathshala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pathshala.DTO.LoginCredDTO;
import com.pathshala.DTO.LoginResultDTO;
import com.pathshala.models.CurrentSession;
import com.pathshala.services.AdminLoginService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminLoginService adminLogin;
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResultDTO> adminLogin(@RequestBody LoginCredDTO loginCred){
	
		return new ResponseEntity<LoginResultDTO> (adminLogin.loginAdmin(loginCred), HttpStatus.OK);
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> adminLogOut(@RequestParam String sessionId){
	
		return new ResponseEntity<String> (adminLogin.logOutAdmin(sessionId), HttpStatus.OK);
	}
	
	
}
