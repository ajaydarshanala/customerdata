package com.tcs.cd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tcs.cd.dto.AddressDTO;
import com.tcs.cd.service.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateCustomerTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(UpdateCustomerTest.class);
	
	@Autowired
	CustomerServiceImpl service;

	public UpdateCustomerTest() {
		
	}
	
	@Test
	public void whenCustomerIdNotExist() {
		
		LOG.info("UpdateCustomerTest.whenCustomerIdNotExist");
		boolean iscustomeridfound = true;

		try {
			// default loaded data - customer ids 1001,1002,1003,1004,1005
			service.getCustomerById("9999");

		} catch (Exception e) {
			iscustomeridfound = false;
			LOG.info("UpdateCustomerTest.whenCustomerIdNotExist.CustomerNotFound "+e.getMessage());
		}
		assertEquals(false, iscustomeridfound);
	}
	
	@Test
	public void updateDetailsWithvaliddata() {
		
		LOG.info("UpdateCustomerTest.updateDetailsWithvaliddata");
		boolean isdetailsupdated = false;
				
		try {
			service.updateAddress("1005", new AddressDTO("46", "DAMSQUARE", "2299XE", "AMSTERDAM"));
			isdetailsupdated = true;
			
		} catch (Exception e) {
			isdetailsupdated = false;
			LOG.info("UpdateCustomerTest.updateDetailsWithvaliddata.CustomerNotFound "+e.getMessage());
		} 
		assertEquals(true, isdetailsupdated);
	}
	
	@Test
	public void updateDetailsEmptyHouseNo() {
		
		LOG.info("UpdateCustomerTest.updateDetailsEmptyHouseNo");
		boolean isdetailsupdated = false;
		
		try {
			service.updateAddress("1005", new AddressDTO(null, "DAMSQUARE", "2299XE", "AMSTERDAM"));
			isdetailsupdated = isdetailsupdated;
			
		} catch (Exception e) {
			isdetailsupdated = false;
			LOG.info("UpdateCustomerTest.updateDetailsWithvaliddata.CustomerNotFound "+e.getMessage());
		}
		assertEquals(true, !isdetailsupdated);
	}
	
	@Test
	public void updateDetailsEmptyStreet() {
		
		LOG.info("UpdateCustomerTest.updateDetailsEmptyStreet");
		boolean isdetailsupdated = false;
		
		try {
			service.updateAddress("1005", new AddressDTO("47", null, "2299XE", "AMSTERDAM"));
			isdetailsupdated = isdetailsupdated;
			
		} catch (Exception e) {
			isdetailsupdated = false;
			LOG.info("UpdateCustomerTest.updateDetailsEmptyStreet.CustomerNotFound "+e.getMessage());
		} 
		assertEquals(true, !isdetailsupdated);
	}
	
	@Test
	public void updateDetailsEmptyPostcode() {
		
		LOG.info("UpdateCustomerTest.updateDetailsEmptyPostcode");
		boolean isdetailsupdated = false;
		
		try {
			service.updateAddress("1005", new AddressDTO("48", "DAMSQUARE", null, "AMSTERDAM"));
			isdetailsupdated = isdetailsupdated;
			
		} catch (Exception e) {
			isdetailsupdated = false;
			LOG.info("UpdateCustomerTest.updateDetailsEmptyPostcode.CustomerNotFound "+e.getMessage());
		} 
		assertEquals(true, !isdetailsupdated);
	}
	
	@Test
	public void updateDetailsEmptyCity() {
		
		LOG.info("UpdateCustomerTest.updateDetailsEmptyCity");
		boolean isdetailsupdated = false;
		
		try {
			service.updateAddress("1005", new AddressDTO("49", "DAMSQUARE", "2299XE", null));
			isdetailsupdated = isdetailsupdated;
			
		} catch (Exception e) {
			isdetailsupdated = false;
			LOG.info("UpdateCustomerTest.updateDetailsEmptyCity.CustomerNotFound "+e.getMessage());
		}
		assertEquals(true, !isdetailsupdated);
	}
	
	
	
	

}
