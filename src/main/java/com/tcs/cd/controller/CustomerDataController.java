package com.tcs.cd.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.cd.dto.AddressDTO;
import com.tcs.cd.dto.CustomerDataDTO;
import com.tcs.cd.entity.Customer;
import com.tcs.cd.exceptions.CustomerNotFound;
import com.tcs.cd.repository.CustomerRepo;
import com.tcs.cd.service.CustomerServiceImpl;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/customerdetails")
public class CustomerDataController {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerDataController.class);

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	CustomerServiceImpl customerServiceImpl;

	public CustomerDataController() {}

	@PostMapping("/add")
	@ApiOperation(value = "Add a new customer")
	public ResponseEntity<?> addDetails(@Valid @RequestBody CustomerDataDTO customerdata){

		LOG.info("CustomerDataController.addCustomerDetails");		
		CustomerDataDTO customerdetaiils = customerServiceImpl.createCustomerDTO(customerRepo.save(customerServiceImpl.createCustomerEntity(customerdata)));
		return new ResponseEntity<CustomerDataDTO>(customerdetaiils,HttpStatus.OK);
	}

	@GetMapping("/all")
	@ApiOperation(value = "Retrieve all customers")
	public ResponseEntity<?> getAllDetails(){

		LOG.info("CustomerDataController.getAllCustomersDetails");		
		List<CustomerDataDTO> detailsList = customerServiceImpl.getAllDetails();
		return new ResponseEntity<List<CustomerDataDTO>>(detailsList,HttpStatus.OK);
	}


	@GetMapping("/customer/{customerid}")
	@ApiOperation(value = "Retrieve one customer by it's identifier")
	public ResponseEntity<?> getDetailsById(@PathVariable("customerid") @Valid @NotBlank String identifier){

		LOG.info("CustomerDataController.getCustomerById");
		Customer customer  = customerRepo.findById(Long.parseLong(identifier)).orElseThrow(() -> new CustomerNotFound("Customer not found with id : "+identifier));
		return new ResponseEntity<CustomerDataDTO>(customerServiceImpl.createCustomerDTO(customer),HttpStatus.OK);
	}


	@GetMapping("/search")
	@ApiOperation(value = "Search customers by first name and/or last name")
	public ResponseEntity<?> getDetailsByName(
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "lastname", required = false) String lastname) {

		LOG.info("CustomerDataController.getCustomerDetailsByNames firstname = "+firstname+" lastname = "+lastname);
		List<CustomerDataDTO> detailsList = customerServiceImpl.getDetailsByName(firstname, lastname);
		return new ResponseEntity<List<CustomerDataDTO>>(detailsList,HttpStatus.OK);
	}


	@PutMapping("/customer/{customerid}/address")
	@ApiOperation(value = "Update the living address")
	public ResponseEntity<?> updateAdressDetails(@PathVariable("customerid") String identifier,@Valid @RequestBody AddressDTO addressdto) {

		LOG.info("CustomerDataController.updateAdress");
		CustomerDataDTO customerdetails = customerServiceImpl.updateAddress(identifier, addressdto);
		return new ResponseEntity<CustomerDataDTO>(customerdetails,HttpStatus.OK);
	}

}
