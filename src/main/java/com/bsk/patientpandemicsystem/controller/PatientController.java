package com.bsk.patientpandemicsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bsk.patientpandemicsystem.dto.PatientDto;
import com.bsk.patientpandemicsystem.dto.PatientMedicalHistoryResponseDto;
import com.bsk.patientpandemicsystem.dto.PatientRequestDto;
import com.bsk.patientpandemicsystem.dto.PatientResponseDto;
import com.bsk.patientpandemicsystem.dto.ResponseDto;
import com.bsk.patientpandemicsystem.service.PatientService;


@RestController
@RequestMapping("/patients")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@PostMapping("/")
	public ResponseEntity<PatientResponseDto> savePatientRecord(@RequestBody PatientRequestDto patient) {
		return new ResponseEntity<PatientResponseDto>(patientService.savePatientRecord(patient), HttpStatus.CREATED);
	}
	
	@GetMapping("/{nationalId}")
	public ResponseEntity<PatientDto> getPatientRecordByNationalId(@PathVariable String nationalId) {
		return new ResponseEntity<PatientDto>(patientService.getPatientRecordByNationalId(nationalId), HttpStatus.OK);
	}
	
//	@GetMapping("/{patientId}")
	public ResponseEntity<PatientMedicalHistoryResponseDto> getPatientMedicalHistoryByPatientId(@PathVariable Integer patientId) {
		return new ResponseEntity<PatientMedicalHistoryResponseDto>(patientService.getPatientMedicalHistoryByPatientId(patientId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDto> deletePatientRecordByPatientId(@PathVariable Integer id) {
		return new ResponseEntity<ResponseDto>(patientService.deletePatientRecordByPatientId(id), HttpStatus.OK);
	}
	
	@GetMapping("/illnessSearch")
	public ResponseEntity<List<PatientDto>> getAllPatientsWithCovid(@RequestParam(required = true) String illness) {
		return new ResponseEntity<List<PatientDto>>(patientService.getAllPatientsWithCovid(illness), HttpStatus.OK);
	}
	
	@GetMapping("/isHospitalised")
	public ResponseEntity<List<PatientDto>> getAllHospitalisedPatients(@RequestParam(required = true) String hospitalAdmission) {
		return new ResponseEntity<List<PatientDto>>(patientService.getAllHospitalisedPatients(hospitalAdmission), HttpStatus.OK);
	}
	
	
	
}
