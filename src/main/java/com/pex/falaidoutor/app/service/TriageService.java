package com.pex.falaidoutor.app.service;

import com.pex.falaidoutor.domain.entity.QueueTriage;
import com.pex.falaidoutor.domain.entity.Triage;
import com.pex.falaidoutor.domain.repository.TriageRepository;
import com.pex.falaidoutor.app.util.Constants;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

        Triage response = this.processRequest(request);

        //Validar queue
        this.checkQueue(Long.parseLong(request.get("queueId")), request.get("queueTicket"));

        // Registrar a triagem no banco de dados e atualizar status
        Triage savedTriage = this.saveTriage(response);
        queueTriageService.linkTriageAndUpdateStatus(Long.parseLong(request.get("queueId")), savedTriage.getId());

        return response;
    }

    public Triage saveTriage(Triage triage) {
        return triageRepository.save(triage);
    }

    public boolean deleteTriage(Long id) {
        if (triageRepository.existsById(id)) {
            triageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public QueueTriage checkQueue(Long queueId, String queueTicket) {
        QueueTriage response = queueTriageService.getValidQueueTriage(queueId, queueTicket);

        return response;
    }

    public Triage processRequest(Map<String, String> request) {

        Triage response;

        if (request == null || request.isEmpty() || request.get("symptoms") == null) {
            response = new Triage(
                    "Empty symptom list",
                    "Cannot return risk without symptoms.",
                    "Cannot return justification without symptoms."
            );
        }

        String symptoms = request.get("symptoms");

        // Enviar sintomas para o ChatBot
        String chatResponse = chatClient.prompt()
                .user(symptoms)
                .call()
                .content();

        String[] riskParts = chatResponse.split("\n\n");
        String risk = this.processRisk(riskParts[0]);
        String justification = riskParts[1];

        // Tratar a justificativa
        if (justification.contains("Justificativa:")) {
            justification = justification.replace("Justificativa:", "").trim();
        }

        response = new Triage(symptoms, risk, justification);
        response.setId(null);
        return response;
    }

    public String processRisk(String risk) {
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

        return risk;
    }

    public Triage getTriageById(Long id) {
        // Implemente a busca pelo repositório
        return triageRepository.findById(id).orElse(null);
    }

    public List<Triage> getAllTriages() {
        // Implemente a listagem pelo repositório
        return triageRepository.findAll();
    }

}

