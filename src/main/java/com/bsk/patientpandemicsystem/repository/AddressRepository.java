package com.bsk.patientpandemicsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bsk.patientpandemicsystem.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	// Assuming each pincode has unique street names
	Optional<Address> findByStreetNameAndPincodeContainingIgnoreCase(String streetName, String pincode);

}
