package com.pex.falaidoutor.service;

import com.pex.falaidoutor.model.entity.Triage;
import com.pex.falaidoutor.repository.TriageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriageService {

    @Autowired
    private TriageRepository triageRepository;

    public List<Triage> listTriages() {
        return triageRepository.findAll();
    }

    public Triage getTriageById(Long id) {
        return triageRepository.findById(id).orElse(null);
    }

    public Triage saveTriage(Triage triage) {
        return triageRepository.save(triage);
    }

    public void deleteTriage(Long id) {
        triageRepository.deleteById(id);
    }

}
