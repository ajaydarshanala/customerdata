package com.tcs.cd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tcs.cd.dto.CustomerDataDTO;
import com.tcs.cd.exceptions.InvalidCustomerData;
import com.tcs.cd.service.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddCustomerDetailsTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(AddCustomerDetailsTest.class);
	
	@Autowired
	CustomerServiceImpl service;

	public AddCustomerDetailsTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void firstNameLastNameBothCannotBeNull() {
		LOG.info("UNIT_TEST : firstNameLastNameBothCannotBeNull");
		CustomerDataDTO customerdata = new CustomerDataDTO(null, null, 30, null);
		boolean isinvaliddata = false;
		
		try {
			service.addDetails(customerdata);
		} catch (Exception e) {
			isinvaliddata = true;
			LOG.info("UNIT_TEST : firstNameLastNameBothCannotBeNull exception :"+e.getMessage() );
			
		}
		assertEquals(true, isinvaliddata);
	}
	
	@Test
	public void ageCannotBeZero() {
		LOG.info("UNIT_TEST : ageCannotBeZero");
		CustomerDataDTO customerdata = new CustomerDataDTO("SMITH", "WILL", 0, null);
		boolean isinvaliddata = false;
		
		try {
			service.addDetails(customerdata);
		} catch (Exception e) {
			isinvaliddata = true;
			LOG.info("UNIT_TEST : ageCannotBeZero exception :"+e.getMessage() );
		}
		assertEquals(true, isinvaliddata);
	}
	
	@Test
	public void addressCannotBeNull() {
		LOG.info("UNIT_TEST : addressCannotBeNull");
		CustomerDataDTO customerdata = new CustomerDataDTO("SMITH", "WILL", 30, null);
		boolean isinvaliddata = false;
		
		try {
			service.addDetails(customerdata);
		} catch (Exception e) {
			isinvaliddata = true;
			LOG.info("UNIT_TEST : addressCannotBeNull exception :"+e.getMessage() );
		}
		assertEquals(true, isinvaliddata);
	}

}
