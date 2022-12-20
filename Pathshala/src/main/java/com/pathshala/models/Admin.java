package com.pathshala.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	
	@Id
	String userName;
	String password;
}
