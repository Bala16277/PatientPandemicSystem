package com.bsk.patientpandemicsystem.dto;

import java.time.LocalDate;

public class PatientMedicalHistoryDto {
	
	private String illness;
	
	private String illnessDesc;
	
	private String hospitalAdmission;
	
	private LocalDate admissionDate;

	private LocalDate dischargedDate;

	private String isAlive;

	private Double charges;

	private Integer patientId;

	private String patient_condition;

	public String getIllness() {
		return illness;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}

	public String getIllnessDesc() {
		return illnessDesc;
	}

	public void setIllnessDesc(String illnessDesc) {
		this.illnessDesc = illnessDesc;
	}

	public String getHospitalAdmission() {
		return hospitalAdmission;
	}

	public void setHospitalAdmission(String hospitalAdmission) {
		this.hospitalAdmission = hospitalAdmission;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public LocalDate getDischargedDate() {
		return dischargedDate;
	}

	public void setDischargedDate(LocalDate dischargedDate) {
		this.dischargedDate = dischargedDate;
	}

	public String getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(String isAlive) {
		this.isAlive = isAlive;
	}

	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatient_condition() {
		return patient_condition;
	}

	public void setPatient_condition(String patient_condition) {
		this.patient_condition = patient_condition;
	}
	
	

}
