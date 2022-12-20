package com.pathshala.models;


import lombok.Data;


@Data
public class StudentAddresses {
	
	private String area;
	private String state;
	private String district;
	private Integer pinCode;
	private String AddressType;
	
}
