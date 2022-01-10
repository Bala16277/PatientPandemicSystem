package com.bsk.patientpandemicsystem.dto;

import java.util.List;

public class PatientMedicalHistoryResponseDto {
	
	private String message;
	
	private Integer status;
	
	private List<PatientMedicalHistoryDto> patientMedicalHistoryDto;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<PatientMedicalHistoryDto> getPatientMedicalHistoryDto() {
		return patientMedicalHistoryDto;
	}

	public void setPatientMedicalHistoryDto(List<PatientMedicalHistoryDto> patientMedicalHistoryDto) {
		this.patientMedicalHistoryDto = patientMedicalHistoryDto;
	}
	
}
