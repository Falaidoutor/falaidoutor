package com.pex.falaidoutor.service;

import com.pex.falaidoutor.model.dto.FinalizedTriageDTO;
import com.pex.falaidoutor.repository.QueueTriageRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.pex.falaidoutor.model.entity.QueueTriage;

import java.util.ArrayList;
import java.util.List;
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

    public Optional<QueueTriage> getValidQueueTriage(Long id, String ticket) {
        return queueTriageRepository.findByIdAndQueueTicket(id, ticket);
    }

    public void linkTriageAndUpdateStatus(Long queueId, Long triageId) {
        queueTriageRepository.linkTriageAndUpdateStatus(queueId, triageId);
    }

    public List<FinalizedTriageDTO> getFinalizedTriages() {
        List<Object[]> results = queueTriageRepository.findAllFinalizedTriageData();
        List<FinalizedTriageDTO> dtos = new ArrayList<FinalizedTriageDTO>();

        System.out.println("Até aqui");

        for (Object[] row : results) {
            Long queue_id = (Long) row[0];
            String name = (String) row[1];
            String gender = String.valueOf(row[2]);
            Integer age = ((Number) row[3]).intValue();
            String queueTicket = String.valueOf(row[4]);
            String risk = String.valueOf(row[5]);

            dtos.add(new FinalizedTriageDTO(queue_id, name, gender, age, queueTicket, risk));
        }

        return dtos;
    }
}
