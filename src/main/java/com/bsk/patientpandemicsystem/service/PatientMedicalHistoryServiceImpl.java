package com.bsk.patientpandemicsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bsk.patientpandemicsystem.dto.PatientMedicalHistoryDto;
import com.bsk.patientpandemicsystem.dto.PatientMedicalHistoryResponseDto;
import com.bsk.patientpandemicsystem.entity.Patient;
import com.bsk.patientpandemicsystem.entity.PatientMedicalHistory;
import com.bsk.patientpandemicsystem.repository.PatientMedicalHistoryRepository;
import com.bsk.patientpandemicsystem.repository.PatientRepository;

@Service
public class PatientMedicalHistoryServiceImpl implements PatientMedicalHistoryService {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PatientMedicalHistoryRepository patientMedicalHistoryRepository;
	
	@Override
	public PatientMedicalHistoryResponseDto getPatientMedicalHistoryByPatientId(Integer patientId) {
		PatientMedicalHistoryResponseDto patientMedicalHistoryResponseDto = new PatientMedicalHistoryResponseDto();
		
		Optional<Patient> patients = patientRepository.findById(patientId);
		if(patients.isPresent()) {
			Patient patient = patients.get();
			Optional<List<PatientMedicalHistory>> patientMedicalHistories = patientMedicalHistoryRepository.findByPatient(patient);
			if(patientMedicalHistories.isPresent()) {
				List<PatientMedicalHistory> patientMedicalHistoriesObj = patientMedicalHistories.get();
				List<PatientMedicalHistoryDto> patientMedicalHistoryDtoList = new ArrayList<>();
				for(PatientMedicalHistory patientMedicalHistory : patientMedicalHistoriesObj) {
					PatientMedicalHistoryDto patientMedicalHistoryDto = new PatientMedicalHistoryDto();
					BeanUtils.copyProperties(patientMedicalHistory, patientMedicalHistoryDto);
					patientMedicalHistoryDto.setPatientId(patientId);
					patientMedicalHistoryDtoList.add(patientMedicalHistoryDto);
				}
				patientMedicalHistoryResponseDto.setMessage("The medical history for the given id: ");
				patientMedicalHistoryResponseDto.setPatientMedicalHistoryDto(patientMedicalHistoryDtoList);
				patientMedicalHistoryResponseDto.setStatus(HttpStatus.OK.value());
				return patientMedicalHistoryResponseDto;
			} else {
				patientMedicalHistoryResponseDto.setMessage("There is no medical history for the given patient id");
				patientMedicalHistoryResponseDto.setStatus(HttpStatus.OK.value());
				return patientMedicalHistoryResponseDto;
			}
		} else {
			patientMedicalHistoryResponseDto.setMessage("There is no patient exists with that id");
			patientMedicalHistoryResponseDto.setStatus(HttpStatus.OK.value()); 
			return patientMedicalHistoryResponseDto;
		}
		
	}

}
