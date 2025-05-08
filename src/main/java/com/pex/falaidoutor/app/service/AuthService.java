package com.pex.falaidoutor.app.service;

import com.pex.falaidoutor.domain.model.dto.TriageAuthResponse;
import com.pex.falaidoutor.domain.model.entity.QueueTriage;
import com.pex.falaidoutor.domain.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public TriageAuthResponse authenticate(String cpf, String queueTicket) {
        Optional<QueueTriage> queueTriage = authRepository.findByPatientCpfAndQueueTicket(cpf, queueTicket);

        if (queueTriage.isPresent()) {
            QueueTriage qt = queueTriage.get();
            String name = qt.getPatient().getName();
            Long id = qt.getId();
            Long status = qt.getStatus().getId();
            return new TriageAuthResponse(true, name, id, status);
        }

        return new TriageAuthResponse(false, null, null, null);
    }
}
