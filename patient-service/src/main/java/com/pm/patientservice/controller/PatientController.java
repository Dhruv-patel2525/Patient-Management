package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.validators.PatientValidatorsGroup;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patients" ,description = "API for managing patients")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService)
    {
        this.patientService = patientService;
    }

    @GetMapping("/")
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>>getAllPatients()
    {
        List<PatientResponseDTO> patientResponseDTOList=patientService.getPatients();
        return ResponseEntity.ok(patientResponseDTOList);
    }

    @PostMapping("/")
    @Operation(summary = "Create a new patient")
    public ResponseEntity<?> createPatient(@Validated({Default.class, PatientValidatorsGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO)
    {
            PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
            return ResponseEntity.ok(patientResponseDTO);

    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO)
    {
        PatientResponseDTO patientResponseDTO=patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id)
    {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
