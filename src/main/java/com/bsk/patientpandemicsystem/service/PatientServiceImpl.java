package com.bsk.patientpandemicsystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bsk.patientpandemicsystem.dto.PatientDto;
import com.bsk.patientpandemicsystem.dto.PatientMedicalHistoryDto;
import com.bsk.patientpandemicsystem.dto.PatientMedicalHistoryResponseDto;
import com.bsk.patientpandemicsystem.dto.PatientRequestDto;
import com.bsk.patientpandemicsystem.dto.PatientResponseDto;
import com.bsk.patientpandemicsystem.dto.ResponseDto;
import com.bsk.patientpandemicsystem.entity.Address;
import com.bsk.patientpandemicsystem.entity.Patient;
import com.bsk.patientpandemicsystem.entity.PatientMedicalHistory;
import com.bsk.patientpandemicsystem.repository.AddressRepository;
import com.bsk.patientpandemicsystem.repository.PatientMedicalHistoryRepository;
import com.bsk.patientpandemicsystem.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	PatientMedicalHistoryRepository patientMedicalHistoryRepository;
	
	

	@Override
	public PatientDto getPatientRecordByNationalId(String nationalId) {
		
		Optional<Patient> patients = patientRepository.getPatientByNationalId(nationalId);
		if(patients.isPresent()) {
			Patient patient = patients.get();
			PatientDto patientDto = new PatientDto();
			patientDto.setAddressId(patient.getAddress().getAddressId());
			BeanUtils.copyProperties(patient, patientDto);
			System.out.println("Patient details: "+patientDto);
			return patientDto;
		}
		else {
			return null;
		}
		
	}



	@Override
	public PatientResponseDto savePatientRecord(PatientRequestDto patientRequestDto) {
		// TODO Auto-generated method stub
		PatientResponseDto patientResponseDto = new PatientResponseDto();
		
		String nationalId = patientRequestDto.getNationalId();
		String patientName = patientRequestDto.getPatientName();
		String patientPhoneNumber = patientRequestDto.getPhoneNumber();
		LocalDate dateOfBirth = patientRequestDto.getDateOfBirth();
		String streetName = patientRequestDto.getStreetName();
		String pincode = patientRequestDto.getPincode();
		String landmark = patientRequestDto.getLandmark();
		String city = patientRequestDto.getCity();
		String state = patientRequestDto.getState();
		
		// Assuming national id is unique to patient
		Optional<Patient> patients = patientRepository.getPatientByNationalId(nationalId);
		if(patients.isPresent()) {
			Patient patient = patients.get();
			PatientDto patientDto = new PatientDto();
			BeanUtils.copyProperties(patient, patientDto);
			patientResponseDto.setMessage("Patient already exists");
			patientResponseDto.setStatus(HttpStatus.OK.value());
			patientResponseDto.setPatientDto(patientDto);
			return patientResponseDto;
		}
		else {
			Patient patientObj = new Patient();
			Optional<Address> addresses = addressRepository.findByStreetNameAndPincodeContainingIgnoreCase(streetName, pincode);
			if(addresses.isPresent()) {
				Address address = addresses.get();
				patientObj.setAddress(address);
				
			} else {
				Address addressObj = new Address();
				addressObj.setStreetName(streetName);
				addressObj.setPincode(pincode);
				addressObj.setLandmark(landmark);
				addressObj.setCity(city);
				addressObj.setState(state);
				patientObj.setAddress(addressObj);
				addressRepository.save(addressObj);
			}
			patientObj.setPatientName(patientName);
			patientObj.setPhoneNumber(patientPhoneNumber);
			patientObj.setDateOfBirth(dateOfBirth);
			patientObj.setNationalId(nationalId);
			patientRepository.save(patientObj);
			PatientDto patientDto = new PatientDto();
			BeanUtils.copyProperties(patientObj, patientDto);
			patientDto.setAddressId(patientObj.getAddress().getAddressId());
			patientResponseDto.setPatientDto(patientDto);
		}
		patientResponseDto.setMessage("Patient record created!");
		patientResponseDto.setStatus(HttpStatus.CREATED.value());
		return patientResponseDto;
		
	}

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
	
	@Override
	public ResponseDto deletePatientRecordByPatientId(Integer patientId) {
		
		ResponseDto responseDto = new ResponseDto();
		Optional<Patient> patients = patientRepository.findById(patientId);
		if(patients.isPresent()) {
			patientRepository.deleteById(patientId);
			responseDto.setMessage("Patient record deleted successfully");
			responseDto.setStatus(HttpStatus.OK.value());
			return responseDto;
		} else {
			responseDto.setMessage("There is no patient record with the given id");
			responseDto.setStatus(HttpStatus.OK.value());
			return responseDto;
		}
	}
	
	@Override
	public List<PatientDto> getAllPatientsWithCovid(String illness) {
		
		List<Patient> patientList = new ArrayList<>();
		List<PatientDto> patientDtoList = new ArrayList<>();
		Optional<List<PatientMedicalHistory>> patientMedicalHistories = patientMedicalHistoryRepository.findByIllnessIgnoreCaseContaining(illness);
		if(patientMedicalHistories.isPresent()) {
			List<PatientMedicalHistory> patientMedicalHistoryList = patientMedicalHistories.get();
			for(PatientMedicalHistory patientMedicalHistory : patientMedicalHistoryList) {
				Patient patient = patientMedicalHistory.getPatient();
				if(!patientList.contains(patient)) {
					patientList.add(patient);
				}
			}
			
			for(Patient patient : patientList) {
				PatientDto patientDto = new PatientDto();
				BeanUtils.copyProperties(patient, patientDto);
				patientDtoList.add(patientDto);
			}
			return patientDtoList;
		} else {
			return Collections.emptyList();
		}
	}
	
	@Override
	public List<PatientDto> getAllHospitalisedPatients(String hospitalAdmission) {
		
		List<Patient> patientList = new ArrayList<>();
		List<PatientDto> patientDtoList = new ArrayList<>();
		Optional<List<PatientMedicalHistory>> patientMedicalHistories = patientMedicalHistoryRepository.findByHospitalAdmissionEqualsIgnoreCase(hospitalAdmission);
		if(patientMedicalHistories.isPresent()) {
			List<PatientMedicalHistory> patientMedicalHistoryList = patientMedicalHistories.get();
			for(PatientMedicalHistory patientMedicalHistory : patientMedicalHistoryList) {
				Patient patient = patientMedicalHistory.getPatient();
				if(!patientList.contains(patient)) {
					patientList.add(patient);
				}
			}
			
			for(Patient patient : patientList) {
				PatientDto patientDto = new PatientDto();
				BeanUtils.copyProperties(patient, patientDto);
				patientDtoList.add(patientDto);
			}
			return patientDtoList;
		} else {
			return Collections.emptyList();
		}
	}
	
	

}
