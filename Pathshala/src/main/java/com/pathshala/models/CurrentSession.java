package com.pathshala.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CurrentSession {

	@Id
	private String sessionId;
	private String userName;
}
