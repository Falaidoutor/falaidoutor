package com.pex.falaidoutor.domain.model;

import lombok.Data;

@Data
public class PatientDTO {
    private Long id;
    private String name;
    private String cpf;
    private int age;
    private char gender;
}
