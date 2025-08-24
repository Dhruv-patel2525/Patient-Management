package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.models.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository)
    {
        this.patientRepository=patientRepository;
    }

    public List<PatientResponseDTO> getPatients()
    {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream().map(PatientMapper::toDTO).toList();
    }
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO)
    {
        Patient newPatient= patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        System.out.println("Saved data successfully");
        return PatientMapper.toDTO(newPatient);
    }
}
