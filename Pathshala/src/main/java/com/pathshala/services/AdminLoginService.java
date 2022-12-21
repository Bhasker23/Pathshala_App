package com.pathshala.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pathshala.DTO.LoginCredDTO;
import com.pathshala.DTO.LoginResultDTO;
import com.pathshala.DTO.StudentResDto;

@Service
public interface AdminLoginService {
	
	public LoginResultDTO loginAdmin(LoginCredDTO loginCred);
	public String logOutAdmin(String sessionId);
	public List<StudentResDto> getAllStudentOfACourse(String sessionid,Integer cid);
	
}
