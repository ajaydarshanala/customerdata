package com.tcs.cd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity(name="address")
public class Address {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private Long address_id;
	
	@Column(name="house_number")
	private String housenumber;
	
	@Column(name="street")
	private String street;
	
	@Column(name="post_code")
	private String postcode;
	
	@Column(name="city")
	private String city;
	
	@OneToOne(mappedBy = "address")
    private Customer customer;
	
	public Address() {}

	public String getHousenumber() {
		return housenumber;
	}

	public Long getAddressid() {
		return address_id;
	}

	public void setAddressid(Long address_id) {
		this.address_id = address_id;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Address(String housenumber, String street, String postcode, String city) {
		super();
		this.housenumber = housenumber;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
	}

	public Address(String housenumber, String street, String postcode, String city, Customer customer) {
		super();
		this.housenumber = housenumber;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
		this.customer = customer;
	}

	public Address(Long address_id, String housenumber, String street, String postcode, String city, Customer customer) {
		super();
		this.address_id = address_id;
		this.housenumber = housenumber;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Address [addressid=" + address_id + ", housenumber=" + housenumber + ", street=" + street + ", postcode="
				+ postcode + ", city=" + city + ", customer=" + customer + "]";
	}
}
