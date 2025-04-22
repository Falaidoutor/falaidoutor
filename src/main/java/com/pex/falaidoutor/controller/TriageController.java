package com.pex.falaidoutor.controller;

import com.pex.falaidoutor.model.dto.TriageDTO;
import com.pex.falaidoutor.model.entity.Triage;
import com.pex.falaidoutor.service.TriageService;
import com.pex.falaidoutor.utils.Constants;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "/api/triage")
@CrossOrigin(origins = "*")
public class TriageController {

    @Autowired
    private TriageService triageService;

    private final ChatClient chatClient;

    public TriageController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem(Constants.systemBehaviour)
                .build();
    }

    @GetMapping
    public ResponseEntity<List<Triage>> listTriages() {
        return ResponseEntity.ok(triageService.listTriages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Triage> getTriage(@PathVariable Long id) {
        Triage triage = triageService.getTriageById(id);
        if (triage != null) {
            return ResponseEntity.ok(triage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Triage> triageChat(@RequestBody(required = false) Map<String, String> request) {

        if (request == null || request.isEmpty()) {
            Triage response = new Triage(
                    "Empty symptom list",
                    "Error: cannot return risk without symptoms.",
                    "Error: cannot return justification without symptoms."
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String symptoms = request.get("symptoms");

        String chatResponse = chatClient.prompt()
                .user(symptoms)
                .call()
                .content();

        String riskParts[] = chatResponse.split("\n\n");
        String risk = riskParts[0];
        String justification = riskParts[1];

        Triage response = new Triage(symptoms, risk, justification);
        response.setId(null);
        triageService.saveTriage(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
