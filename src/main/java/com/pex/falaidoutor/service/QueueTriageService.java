package com.pex.falaidoutor.service;

import com.pex.falaidoutor.model.dto.FinalizedTriageDTO;
import com.pex.falaidoutor.model.dto.TriageListDTO;
import com.pex.falaidoutor.repository.QueueTriageRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.pex.falaidoutor.model.entity.QueueTriage;

import java.time.LocalDateTime;
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
