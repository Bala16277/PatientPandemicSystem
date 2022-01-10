package com.bsk.patientpandemicsystem.service;

import java.util.List;

import com.bsk.patientpandemicsystem.dto.PatientDto;
import com.bsk.patientpandemicsystem.dto.PatientMedicalHistoryResponseDto;
import com.bsk.patientpandemicsystem.dto.PatientRequestDto;
import com.bsk.patientpandemicsystem.dto.PatientResponseDto;
import com.bsk.patientpandemicsystem.dto.ResponseDto;

public interface PatientService {
	
	public PatientDto getPatientRecordByNationalId(String nationalId);

	public PatientResponseDto savePatientRecord(PatientRequestDto patient);

	public PatientMedicalHistoryResponseDto getPatientMedicalHistoryByPatientId(Integer patientId);

	public ResponseDto deletePatientRecordByPatientId(Integer id);

	public List<PatientDto> getAllPatientsWithCovid(String illness);
	
	public List<PatientDto> getAllHospitalisedPatients(String hospitalAdmission);



	


}
