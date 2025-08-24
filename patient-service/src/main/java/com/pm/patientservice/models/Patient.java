package com.pm.patientservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Email
    @Column(unique = true,name = "email")
    private String email;

    @NotNull
    @Column(name="address")
    private String address;

    @NotNull
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name="registeredDate")
    private LocalDate registeredDate;
}
