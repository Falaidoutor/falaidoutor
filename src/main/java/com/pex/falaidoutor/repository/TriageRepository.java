package com.pex.falaidoutor.repository;

import com.pex.falaidoutor.model.entity.Triage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TriageRepository extends JpaRepository<Triage, Long> {

    Triage save(Triage triage);

    Optional<Triage> findById(Long id);

    void deleteById(Long id);

    List<Triage> findAll();

}
