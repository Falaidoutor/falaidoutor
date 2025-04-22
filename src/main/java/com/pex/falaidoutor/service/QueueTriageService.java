package com.pex.falaidoutor.service;

import com.pex.falaidoutor.repository.QueueTriageRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.pex.falaidoutor.model.entity.QueueTriage;
import java.util.Optional;
import com.pex.falaidoutor.model.dto.TriageAuthResponse;


@Service
public class QueueTriageService {
    @Autowired
    private QueueTriageRepository queueTriageRepository;

    public TriageAuthResponse authenticate(String cpf, String queueTicket) {
        Optional<QueueTriage> queueTriage = queueTriageRepository.findByPatientCpfAndQueueTicket(cpf, queueTicket);

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
