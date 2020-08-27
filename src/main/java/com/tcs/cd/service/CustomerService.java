package com.tcs.cd.service;

import java.util.List;

import com.tcs.cd.dto.AddressDTO;
import com.tcs.cd.dto.CustomerDataDTO;
import com.tcs.cd.exceptions.CustomerNotFound;
import com.tcs.cd.exceptions.InvalidCustomerData;

public interface CustomerService {
	
	public abstract CustomerDataDTO addDetails(CustomerDataDTO customerdata) throws InvalidCustomerData, IllegalArgumentException;
	
	public abstract CustomerDataDTO getCustomerById(String customerid) throws CustomerNotFound,InvalidCustomerData;
	
	public abstract List<CustomerDataDTO> getAllDetails() throws CustomerNotFound;
	
	public abstract List<CustomerDataDTO> getDetailsByName(String firstname,String lastname) throws CustomerNotFound, InvalidCustomerData ;
	
	public abstract CustomerDataDTO updateAddress(String customerid,AddressDTO addressdto) throws CustomerNotFound, InvalidCustomerData;
	
	
}
