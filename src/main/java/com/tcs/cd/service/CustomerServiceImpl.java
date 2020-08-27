package com.tcs.cd.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.cd.dto.AddressDTO;
import com.tcs.cd.dto.CustomerDataDTO;
import com.tcs.cd.entity.Address;
import com.tcs.cd.entity.Customer;
import com.tcs.cd.exceptions.CustomerNotFound;
import com.tcs.cd.exceptions.InvalidCustomerData;
import com.tcs.cd.repository.AddressRepo;
import com.tcs.cd.repository.CustomerRepo;
import com.tcs.cd.util.AppUtils;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	AddressRepo addressRepo;

	@Autowired
	private EntityManager em;

	private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

	public CustomerServiceImpl() {}

	@Override
	public CustomerDataDTO addDetails(@Valid CustomerDataDTO details) throws InvalidCustomerData, IllegalArgumentException{

		LOG.info("In CustomerServiceImpl.processCustomerData customerdetails : "+details.toString());
		Customer rs_customer = customerRepo.save(createCustomerEntity(details));
		return createCustomerDTO(rs_customer);
	}
	
	public List<CustomerDataDTO> getAllDetails() throws CustomerNotFound{

		LOG.info("In CustomerServiceImpl.getAllCustomersDetails");
		List<Customer> customer =  customerRepo.findAll();
		if(AppUtils.IsNullOrEmpty(customer)) {throw new CustomerNotFound("No Customer(s) found");}
		List<CustomerDataDTO> detailsList = new ArrayList<CustomerDataDTO>();
		// get all
		customer.forEach(e->detailsList.add(createCustomerDTO(e)));
		return detailsList;
	}
	
	@Override
	public CustomerDataDTO getCustomerById(String customerid) throws CustomerNotFound,InvalidCustomerData {

		LOG.info("In CustomerServiceImpl.getCustomerById customerid = "+customerid);
		// Validate 		
		if(!AppUtils.isnumberstring(customerid)) {throw new InvalidCustomerData("Identifier must contain number only");}
		Customer customer = customerRepo.findById(Long.parseLong(customerid)).orElseThrow(() -> new CustomerNotFound("No Customer found with Id "+customerid));
		return createCustomerDTO(customer);

	}
	

	@Override
	public List<CustomerDataDTO> getDetailsByName(String firstname,String lastname) throws CustomerNotFound, InvalidCustomerData {

		LOG.info("In CustomerServiceImpl.getCustomerDetailsByNames firstname = "+firstname+" lastname = "+lastname);
		if(AppUtils.isNull(firstname) && AppUtils.isNull(lastname)) {throw new InvalidCustomerData("Search cannot be performed with Firstname and Lastname being null");} 
		else if(!AppUtils.isNull(firstname)&&firstname.isBlank()) {throw new InvalidCustomerData("First Name cannot be blank or whitespaces, Either provide a value or remove firstname from search");} 
		else if(!AppUtils.isNull(lastname)&&lastname.isBlank()) {throw new InvalidCustomerData("Last Name cannot be blank or whitespaces, Either provide a value or remove lastname from search");}	

		// Initialize
		Predicate cond = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> root = cq.from(Customer.class);
		List<Predicate> predicateList = new ArrayList<Predicate>();
		List<Customer> customerentitylist = null;

		if(!AppUtils.isNull(firstname) && AppUtils.isNull(lastname)) {
			cond = cb.equal(root.get("firstname"),firstname);
			predicateList.add(cond);
		} 
		else if(AppUtils.isNull(firstname) && !AppUtils.isNull(lastname)) {
			cond = cb.equal(root.get("lastname"),lastname);
			predicateList.add(cond);
		} 
		else {
			cond = cb.equal(root.get("firstname"),firstname);
			predicateList.add(cond);
			cond = cb.equal(root.get("lastname"),lastname);
			predicateList.add(cond);
		}
		LOG.info("In predicateList = "+predicateList.toString());
		// Initilize the persistance objects

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);
		cq.where(predArray);
		TypedQuery<Customer> query = em.createQuery(cq);

		// Get result
		customerentitylist  = query.getResultList();
		if(customerentitylist.isEmpty()) {
			throw new CustomerNotFound("No details found for the search criteria");
		}
		LOG.info("Result entity size = "+customerentitylist.size());
		List<CustomerDataDTO> detailsList = new ArrayList<CustomerDataDTO>();

		// Iterate and map to response dto
		customerentitylist.forEach(e->detailsList.add(createCustomerDTO(e)));
		return detailsList;
	}
	
	@Override
	@Transactional
	public CustomerDataDTO updateAddress(String customerid,AddressDTO addressdto) throws CustomerNotFound, InvalidCustomerData{

		LOG.info("In CustomerServiceImpl.updateDetails customerid = "+customerid);

		// check customer exist already
		Customer customer = customerRepo.findById(Long.parseLong(customerid)).orElseThrow(() -> new CustomerNotFound("No Customer found with Id "+customerid));
		
		if(!AppUtils.isNull(addressdto.getHousenumber())) {customer.getAddress().setHousenumber(addressdto.getHousenumber());}
		if(!AppUtils.isNull(addressdto.getStreet())) {customer.getAddress().setStreet(addressdto.getStreet());}
		if(!AppUtils.isNull(addressdto.getPostcode())) {customer.getAddress().setPostcode(addressdto.getPostcode());}
		if(!AppUtils.isNull(addressdto.getCity())) {customer.getAddress().setCity(addressdto.getCity());}

		// Update address
		Address address = customer.getAddress();
		addressRepo.updateAddress(address.getHousenumber(), address.getStreet(), address.getPostcode(), address.getCity(), Long.parseLong(customerid));
		Customer updatedcustomer = customerRepo.findById(Long.parseLong(customerid)).get();

		return createCustomerDTO(updatedcustomer);
	}


	public CustomerDataDTO createCustomerDTO(Customer customer) {
		LOG.info("In createCustomerDTO");
		return new CustomerDataDTO(
				customer.getCustomerid(),
				customer.getFirstname(),
				customer.getLastname(),
				customer.getAge(),
				new AddressDTO(
						customer.getAddress().getHousenumber(),
						customer.getAddress().getStreet(),
						customer.getAddress().getPostcode(),
						customer.getAddress().getCity()));
	}
	public Customer createCustomerEntity(CustomerDataDTO details) {
		return new
				Customer(
						details.getFirstname(),
						details.getLastname(),
						details.getAge(), 
						new Address(
								details.getAddress().getHousenumber(),
								details.getAddress().getStreet(),
								details.getAddress().getPostcode(),
								details.getAddress().getCity()));
	}
}

