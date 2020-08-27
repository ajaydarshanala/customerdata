package com.tcs.cd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.tcs.cd.exceptions.InvalidCustomerData;
import com.tcs.cd.service.CustomerServiceImpl;
import com.tcs.cd.util.AppUtils;
import com.tcs.cd.controller.CustomerDataController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchCustomerTest {

	private static final Logger LOG = LoggerFactory.getLogger(SearchCustomerTest.class);

	@Autowired
	CustomerServiceImpl service;

	@Autowired
	CustomerDataController controller;

	public SearchCustomerTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void searchWithNullNames() {
		// Search cannot be performed with Firstname and Lastname being null

		LOG.info("SearchCustomerTest.searchWithNullNames");
		boolean issearchok = false;
		try {
			ResponseEntity<?> response = controller.getDetailsByName(null,null);
			if(AppUtils.isNull(response)) {
				throw new InvalidCustomerData("response null");
			}
			LOG.info("SearchCustomerTest.searchWithNullNames--"+response.getStatusCode());
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				issearchok = true;
			}
		}catch(Exception e) {
			issearchok = false;
		}
		assertEquals(false, issearchok);

	}
	
	@Test
	public void searchWithFirstNamesAsBlank() {
		// Search cannot be performed with Firstname as empty or blank

		LOG.info("SearchCustomerTest.searchWithFirstNamesAsBlank");
		boolean issearchok = false;
		try {
			ResponseEntity<?> response = controller.getDetailsByName("             ",null);
			if(AppUtils.isNull(response)) {
				throw new InvalidCustomerData("response null");
			}
			LOG.info("SearchCustomerTest.searchWithFirstNamesAsBlank--"+response.getStatusCode());
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				issearchok = true;
			}
		}catch(Exception e) {
			issearchok = false;
		}
		assertEquals(false, issearchok);

	}
	
	@Test
	public void searchWithLastNamesAsBlank() {
		// Search cannot be performed with Lastname as empty or blank

		LOG.info("SearchCustomerTest.searchWithLastNamesAsBlank");
		boolean issearchok = false;
		try {
			ResponseEntity<?> response = controller.getDetailsByName(null,"   ");
			if(AppUtils.isNull(response)) {
				throw new InvalidCustomerData("response null");
			}
			LOG.info("SearchCustomerTest.searchWithLastNamesAsBlank--"+response.getStatusCode());
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				issearchok = true;
			}
		}catch(Exception e) {
			issearchok = false;
		}
		assertEquals(false, issearchok);

	}

	@Test
	public void whenBothNamesareValid() {
		// Search is possible

		LOG.info("SearchCustomerTest.whenBothNamesareValid");
		boolean issearchok = false;
		try {
			ResponseEntity<?> response = controller.getDetailsByName("STEFAN","ALEX");
			if(AppUtils.isNull(response)) {
				throw new InvalidCustomerData("response null");
			}
			LOG.info("SearchCustomerTest.whenBothNamesareValid--"+response.getStatusCode());
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				issearchok = true;
			}
		}catch(Exception e) {
			issearchok = false;
		}
		assertEquals(true, issearchok);

	}



}
