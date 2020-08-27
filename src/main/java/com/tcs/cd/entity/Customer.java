package com.tcs.cd.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity(name="customer")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long customerid;
	
	@Column(name="first_name")
	private String firstname;	
	
	@Column(name="last_name")
	private String lastname;
	
	@Column(name="age")
	private int age;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
	private Address address;
	
	public Customer() {	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer(String firstname, String lastname, int age, Address address) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.address = address;
	}

	public Customer(Long customerid, String firstname, String lastname, int age, Address address) {
		super();
		this.customerid = customerid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", firstname=" + firstname + ", lastname=" + lastname + ", age="
				+ age + ", address=" + address + "]";
	}	
}
