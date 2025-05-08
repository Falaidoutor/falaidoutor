package com.pex.falaidoutor.app.service;

import com.pex.falaidoutor.domain.model.entity.QueueTriage;
import com.pex.falaidoutor.domain.model.entity.Triage;
import com.pex.falaidoutor.domain.repository.TriageRepository;
import com.pex.falaidoutor.infra.utils.Constants;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TriageService {

    @Autowired private TriageRepository triageRepository;

    @Autowired private QueueTriageService queueTriageService;

    private final ChatClient chatClient;

    public TriageService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem(Constants.systemBehaviour)
                .build();
    }

    public Triage createTriage(Map<String, String> request) {

        Triage response = null;

        if (request == null || request.isEmpty()) {
            response = new Triage(
                    "Empty symptom list",
                    "Cannot return risk without symptoms.",
                    "Cannot return justification without symptoms."
            );
        }

        String symptoms = request.get("symptoms");
        String queueTicket = request.get("queueTicket");
        String queueIdStr = request.get("queueId");
        Long queueId = Long.parseLong(queueIdStr);

        //Validar queue
        QueueTriage queueTriage = queueTriageService.getValidQueueTriage(queueId, queueTicket);

        //Seguir fluxo da IA
        String chatResponse = chatClient.prompt()
                .user(symptoms)
                .call()
                .content();

        String riskParts[] = chatResponse.split("\n\n");
        String risk = riskParts[0];
        String justification = riskParts[1];

        // Tratar a classificação de risco
        if (risk.contains("Urgente")) {
            risk = "Urgente";
        } else if (risk.contains("Grave")) {
            risk = "Grave";
        } else if (risk.contains("Moderado")) {
            risk = "Moderado";
        } else if (risk.contains("Baixo")) {
            risk = "Baixo";
        } else if (risk.contains("Não urgente")) {
            risk = "Não urgente";
        }

        // Tratar a justificativa
        if (justification.contains("Justificativa:")) {
            justification = justification.replace("Justificativa:", "").trim();
        }

        response = new Triage(symptoms, risk, justification);
        response.setId(null);
        Triage savedTriage = this.saveTriage(response);

        //Salvar a triagem na queue e atualizar status para 1
        queueTriageService.linkTriageAndUpdateStatus(queueId, savedTriage.getId());

        return response;
    }

    public Triage saveTriage(Triage triage) {
        return triageRepository.save(triage);
    }

    public void deleteTriage(Long id) {
        triageRepository.deleteById(id);
    }

}
