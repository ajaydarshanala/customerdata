package com.tcs.cd.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CustomerDataDTO {

	private Long customerid;
	
	@NotBlank(message = "First name cannot be null")
	@Size(min=2,message = "First name should have minimum 3 character ")
	private String firstname;
	
	@NotBlank(message = "Last name cannot be null")
	@Size(min=2,message = "Last name should have minimum 3 character ")
	private String lastname;
	
	@Min(value=1,message = "Age cannot be 0")
	private int age;
	
	@NotNull(message = "Address is mandatory")
	private AddressDTO address;


	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public CustomerDataDTO() {

	}

	public CustomerDataDTO(Long customerid, String firstname, String lastname, int age, AddressDTO address) {
		super();
		this.customerid = customerid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.address = address;
	}

	public CustomerDataDTO(String firstname, String lastname, int age, AddressDTO address) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.address = address;
	}

	@Override
	public String toString() {
		return "CustomerDataDTO [customerid=" + customerid + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", age=" + age + ", address=" + address + "]";
	}	
}
