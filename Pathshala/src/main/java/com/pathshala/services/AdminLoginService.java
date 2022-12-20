package com.pathshala.services;

import org.springframework.stereotype.Service;

import com.pathshala.DTO.LoginCredDTO;
import com.pathshala.DTO.LoginResultDTO;

@Service
public interface AdminLoginService {
	
	public LoginResultDTO loginAdmin(LoginCredDTO loginCred);
	public String logOutAdmin(String sessionId);
	
}
