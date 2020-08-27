package com.tcs.cd.dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int responsecode;
	
	private String responsedescription;

	public ResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(int responsecode) {
		this.responsecode = responsecode;
	}

	public String getResponsedescription() {
		return responsedescription;
	}

	public void setResponsedescription(String responsedescription) {
		this.responsedescription = responsedescription;
	}

	@Override
	public String toString() {
		return "ResponseDTO [responsecode=" + responsecode + ", responsedescription=" + responsedescription + "]";
	}

	public ResponseDTO(int responsecode, String responsedescription) {
		super();
		this.responsecode = responsecode;
		this.responsedescription = responsedescription;
	}
}
