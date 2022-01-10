package com.bsk.patientpandemicsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bsk.patientpandemicsystem.entity.Patient;
import com.bsk.patientpandemicsystem.entity.PatientMedicalHistory;

@Repository
public interface PatientMedicalHistoryRepository extends JpaRepository<PatientMedicalHistory, Integer> {
	
	Optional<List<PatientMedicalHistory>> findByPatient(Patient patient);
	
	Optional<List<PatientMedicalHistory>> findByIllnessIgnoreCaseContaining(String illness);

	Optional<List<PatientMedicalHistory>> findByHospitalAdmissionEqualsIgnoreCase(String hospitalAdmission);
	
}
