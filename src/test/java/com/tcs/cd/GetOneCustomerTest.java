package com.tcs.cd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tcs.cd.exceptions.CustomerNotFound;
import com.tcs.cd.service.CustomerServiceImpl;
import com.tcs.cd.dto.CustomerDataDTO;
import com.tcs.cd.util.AppUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetOneCustomerTest {

	private static final Logger LOG = LoggerFactory.getLogger(GetOneCustomerTest.class);

	@Autowired
	CustomerServiceImpl service;
	
	public GetOneCustomerTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void whencustomerIdNumberOnly() {
		
		LOG.info("GetOneCustomerTest.customerIdNumberOnly");
		boolean isvalidcustomerid = false;
		String customerid =  "1001A";
		
		if(AppUtils.isnumberstring(customerid)) {
			isvalidcustomerid = true;
		}
		assertEquals(false, isvalidcustomerid);
	}

	@Test
	public void whenCustomerIdNotExist() {
		
		LOG.info("GetOneCustomerTest.whenCustomerIdNotExist");
		boolean iscustomeridfound = true;

		try {
			// default loaded data - customer ids 1001,1002,1003,1004,1005
			service.getCustomerById("9999");

		} catch (CustomerNotFound e) {
			iscustomeridfound = false;
			LOG.info("GetOneCustomerTest.whenCustomerIdNotExist.CustomerNotFound");
		} catch(Exception e) {
			LOG.info("GetOneCustomerTest.whenCustomerIdNotExist.Exception");
		}
		assertEquals(false, iscustomeridfound);
	}

	@Test
	public void whenCustomerIdEmptyOrWhitespace(){
		
		LOG.info("GetOneCustomerTest.whenCustomerIdEmptyOrWhitespace");
		boolean isvalidcustomerid = true;
		String customerid =  "     ";
		if(customerid.isBlank()) {
			isvalidcustomerid = false;
		}
		assertEquals(false, isvalidcustomerid);
	}
	
	@Test
	public void whenCustomerIdExist() {
		
		LOG.info("GetOneCustomerTest.whenCustomerIdExist");
		boolean iscustomeridfound = false;
		
		try {
			// default loaded data - customer ids 1001,1002,1003,1004,1005
			CustomerDataDTO details = service.getCustomerById("1001");
			if(!AppUtils.isNull(details)) {
				iscustomeridfound = true;
			}

		} catch (CustomerNotFound e) {
			LOG.info("GetOneCustomerTest.whenCustomerIdExist.CustomerNotFound");
		} catch(Exception e) {
			LOG.info("GetOneCustomerTest.whenCustomerIdExist.Exception");
		}
		assertEquals(true, iscustomeridfound);

	}

}
