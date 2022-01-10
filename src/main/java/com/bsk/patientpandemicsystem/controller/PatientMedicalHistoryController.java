package com.bsk.patientpandemicsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsk.patientpandemicsystem.dto.PatientMedicalHistoryResponseDto;
import com.bsk.patientpandemicsystem.service.PatientMedicalHistoryService;

@RestController
@RequestMapping("/patientMedicalHistories")
public class PatientMedicalHistoryController {
	
	@Autowired
	PatientMedicalHistoryService patientMedicalHistoryService;
	
	@GetMapping("/{patientId}")
	public ResponseEntity<PatientMedicalHistoryResponseDto> getPatientMedicalHistoryByPatientId(@PathVariable Integer patientId) {
		return new ResponseEntity<PatientMedicalHistoryResponseDto>(patientMedicalHistoryService.getPatientMedicalHistoryByPatientId(patientId), HttpStatus.OK);
	}

}
