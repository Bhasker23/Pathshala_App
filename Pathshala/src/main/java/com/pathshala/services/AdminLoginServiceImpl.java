package com.pathshala.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathshala.DTO.LoginCredDTO;
import com.pathshala.DTO.LoginResultDTO;
import com.pathshala.GlobalExceptionHandler.LoginException;
import com.pathshala.models.CurrentSession;
import com.pathshala.repo.AdminRepo;
import com.pathshala.repo.CurrentSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	CurrentSessionRepo cs;
	
	@Override
	public LoginResultDTO loginAdmin(LoginCredDTO loginCred) {
		
		if(adminRepo.findById(loginCred.getUserName()).isEmpty()) {
			throw new LoginException("Please Enter Valid Credential");
		}
		
		if(cs.findByUserName(loginCred.getUserName()) != null) {
			throw new LoginException( loginCred.getUserName()+" is already login");
		}
		
		String uniqueID = RandomString.make(5);
		
		LoginResultDTO loginRes = new LoginResultDTO();
		
		loginRes.setSessionID(uniqueID);
		loginRes.setMessage("You are logged in");
		
		CurrentSession crSession = new CurrentSession();
		crSession.setSessionId(uniqueID);
		crSession.setUserName(loginCred.getUserName());
		cs.save(crSession);
		
		return loginRes;
	}

	@Override
	public String logOutAdmin(String sessionId) {
		
		
		if(cs.findById(sessionId).isEmpty()) {
			throw new LoginException(" You are not logged In");
		}		
		
		cs.deleteById(sessionId);
		
		return "logged out Successfully";
	}

}
