package com.tcs.cd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.cd.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findById(Long customerid);
	
	List<Customer> findAll();
	
	@SuppressWarnings("unchecked")
	Customer save(Customer customer_e);	

}
