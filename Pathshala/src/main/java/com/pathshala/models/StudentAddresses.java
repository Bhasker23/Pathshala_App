package com.pathshala.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class StudentAddresses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String area;
	private String state;
	private String district;
	private Integer pincode;
	private AddressType addressType;
	
}
