package com.tcs.cd.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcs.cd.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

	
	  @Modifying
	  @Transactional
	  @Query(value = "UPDATE address A SET " + "A.housenumber = :housenumber, A.street = :street, A.postcode = :postcode, A.city = :city " +
	  "WHERE A.address_id = (SELECT C.address.address_id FROM customer C WHERE C.customerid = :customerid)"
	  ) int updateAddress(@Param("housenumber")String housenumber,	  @Param("street")String street,
	  @Param("postcode")String postcode,@Param("city")String city,  @Param("customerid")Long customerid);
}
