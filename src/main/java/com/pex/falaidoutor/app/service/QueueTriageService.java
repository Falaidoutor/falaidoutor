package com.pex.falaidoutor.app.service;

import com.pex.falaidoutor.domain.model.FinalizedTriageDTO;
import com.pex.falaidoutor.domain.model.TriageListDTO;
import com.pex.falaidoutor.domain.repository.QueueTriageRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.pex.falaidoutor.domain.entity.QueueTriage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QueueTriageService {
    @Autowired private QueueTriageRepository queueTriageRepository;

    public QueueTriage getValidQueueTriage(Long id, String ticket) {
        Optional<QueueTriage> response = queueTriageRepository.findByIdAndQueueTicket(id, ticket);

        if (response.isEmpty() || response.get().getStatus().getId() != 0) {
            throw new RuntimeException("Ficha inválida.");
        }

        return response.get();
    }

    public void linkTriageAndUpdateStatus(Long queueId, Long triageId) {
        queueTriageRepository.linkTriageAndUpdateStatus(queueId, triageId);
    }

    public List<TriageListDTO> getFinalizedTriages() {
        List<Object[]> results = queueTriageRepository.findAllFinalizedTriageData();
        List<TriageListDTO> dtos = new ArrayList<TriageListDTO>();

        for (Object[] row : results) {
            Long queue_id = (Long) row[0];
            String name = (String) row[1];
            String gender = String.valueOf(row[2]);
            Integer age = ((Number) row[3]).intValue();
            String queueTicket = String.valueOf(row[4]);
            String risk = String.valueOf(row[5]);

            dtos.add(new TriageListDTO(queue_id, name, gender, age, queueTicket, risk));
        }

        return dtos;
    }

    public Optional<FinalizedTriageDTO> getQueueTriageById(Long id) {
        QueueTriage triage = queueTriageRepository.findById(id).orElse(null);

        FinalizedTriageDTO dto = new FinalizedTriageDTO(
                triage.getId(),
                triage.getPatient().getName(),
                triage.getPatient().getGender(),
                triage.getPatient().getAge(),
                triage.getQueueTicket(),
                triage.getTriage().getSymptoms(),
                triage.getTriage().getRisk(),
                triage.getTriage().getJustification(),
                triage.getCreatedAt()
        );

        return Optional.of(dto);
    }
}
