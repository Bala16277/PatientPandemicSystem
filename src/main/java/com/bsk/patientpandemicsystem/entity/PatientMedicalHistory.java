package com.bsk.patientpandemicsystem.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient_medical_history")
public class PatientMedicalHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pmh_id")
	private Integer patientMedicalHistoryId;
	
	@Column(name = "illness")
	private String illness;
	
	@Column(name = "illness_desc")
	private String illnessDesc;
	
	@Column(name = "hospital_admission")
	private String hospitalAdmission;
	
	@Column(name = "admission_date")
	private LocalDate admissionDate;
	
	@Column(name = "discharged_date")
	private LocalDate dischargedDate;
	
	@Column(name = "is_alive")
	private String isAlive;
	
	@Column(name = "charges")
	private Double charges;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	@Column(name = "patient_condition")
	private String patient_condition;

	public Integer getPatientMedicalHistoryId() {
		return patientMedicalHistoryId;
	}

	public void setPatientMedicalHistoryId(Integer patientMedicalHistoryId) {
		this.patientMedicalHistoryId = patientMedicalHistoryId;
	}

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

	public String getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(String isAlive) {
		this.isAlive = isAlive;
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

	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getPatient_condition() {
		return patient_condition;
	}

	public void setPatient_condition(String patient_condition) {
		this.patient_condition = patient_condition;
	}


}
