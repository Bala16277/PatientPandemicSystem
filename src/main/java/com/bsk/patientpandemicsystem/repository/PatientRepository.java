package com.bsk.patientpandemicsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bsk.patientpandemicsystem.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	Optional<Patient> getPatientByNationalId(String nationalId);

}
