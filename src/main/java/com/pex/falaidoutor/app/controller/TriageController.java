package com.pex.falaidoutor.app.controller;

import com.pex.falaidoutor.domain.model.entity.QueueTriage;
import com.pex.falaidoutor.domain.model.entity.Triage;
import com.pex.falaidoutor.app.service.QueueTriageService;
import com.pex.falaidoutor.app.service.TriageService;
import com.pex.falaidoutor.infra.utils.Constants;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping( "/triage")
@CrossOrigin(origins = "*")
public class TriageController {

    @Autowired
    private TriageService triageService;

    @Autowired
    private QueueTriageService QTService;

    private final ChatClient chatClient;

    public TriageController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem(Constants.systemBehaviour)
                .build();
    }

    @PostMapping(value = "/chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Triage> triageChat(@RequestBody(required = false) Map<String, String> request) {

        Triage response = triageService.createTriage(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/mock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Triage> triageChatTest(@RequestBody(required = false) Map<String, String> request) {

        if (request == null || request.isEmpty()) {
            Triage response = new Triage(
                    "Empty symptom list",
                    "Error: cannot return risk without symptoms.",
                    "Error: cannot return justification without symptoms."
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String symptoms = request.get("symptoms");

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

        Triage response = new Triage(symptoms, risk, justification);
        response.setId(null);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
