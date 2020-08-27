package com.tcs.cd;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tcs.cd.dto.CustomerDataDTO;
import com.tcs.cd.exceptions.CustomerNotFound;
import com.tcs.cd.service.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetAllCustomersTest {

	@Autowired
	CustomerServiceImpl service;
	
	private static final Logger LOG = LoggerFactory.getLogger(GetAllCustomersTest.class);
	
	public GetAllCustomersTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void whenCustomerDataExist() {
		LOG.info("GetAllCustomersTest.whenCustomerDataExist");
		boolean isdetailsexist = false;
		
		try {
			// There are some customer details exist by default as i am loading data on application startup 
			List<CustomerDataDTO>  detailslist = service.getAllDetails();
			if(!detailslist.isEmpty()) {
				isdetailsexist = true;
				LOG.info("UNIT_TEST : whenCustomerDataExist deatils count : "+detailslist.size());
			}
		} catch (CustomerNotFound e) {
			LOG.info("UNIT_TEST : whenCustomerDataExist exception :");
		}
		assertEquals(true, isdetailsexist);
	}
	
	@Test
	public void whenNOCustomerDataExist() {
		LOG.info("GetAllCustomersTest.whenNOCustomerDataExist");
		boolean isdetailsexist = false;
		
		try {
			/*
			 * This test case is never possible as we are loading some data on application
			 * bootstrap. To test this scenario , remove all entries from data.sql file in resource directory
			 */ 
			List<CustomerDataDTO>  detailslist = service.getAllDetails();
			
		} catch (CustomerNotFound e) {
			LOG.info("UNIT_TEST : whenNOCustomerDataExist exception :");
		}
		assertEquals(false, isdetailsexist);
	}
	
	
}
