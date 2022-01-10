package com.bsk.patientpandemicsystem.service;

import com.bsk.patientpandemicsystem.dto.PatientMedicalHistoryResponseDto;

public interface PatientMedicalHistoryService {
	
	public PatientMedicalHistoryResponseDto getPatientMedicalHistoryByPatientId(Integer patientId);

}
