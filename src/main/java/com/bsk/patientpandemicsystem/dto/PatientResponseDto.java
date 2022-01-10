package com.bsk.patientpandemicsystem.dto;

public class PatientResponseDto extends ResponseDto {
	
	private String message;
	
	private Integer status;
	
	private PatientDto patientDto;

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

	public PatientDto getPatientDto() {
		return patientDto;
	}

	public void setPatientDto(PatientDto patientDto) {
		this.patientDto = patientDto;
	}
	
	

}
