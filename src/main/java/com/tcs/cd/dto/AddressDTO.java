package com.tcs.cd.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

public class AddressDTO {	
	
	@NotBlank(message = "housenumber name cannot be null or empty")
	@Size(min=1,message = "housenumber should have minimum 1 character ")
	private String housenumber;
	
	@NotBlank(message = "street name cannot be null or empty")
	@Size(min=2,message = "street should have minimum 2 character ")
	private String street;
	
	@NotBlank(message = "postcode name cannot be null or empty")
	@Size(min=6,message = "postcode should have minimum 6 character ")
	private String postcode;
	
	@NotBlank(message = "city cannot be null or empty")
	@Size(min=2,message = "city should have minimum 2 character ")
	private String city;
	
	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public AddressDTO() {
	
	}

	public AddressDTO(String housenumber, String street, String postcode, String city) {
		super();
		this.housenumber = housenumber;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
	}

	@Override
	public String toString() {
		return "AddressDTO [housenumber=" + housenumber + ", street=" + street + ", postcode=" + postcode + ", city="
				+ city + "]";
	}	

}
