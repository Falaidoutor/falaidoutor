package com.pex.falaidoutor.domain.repository;

import com.pex.falaidoutor.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
