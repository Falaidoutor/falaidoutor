package com.pex.falaidoutor.controller;

import com.pex.falaidoutor.service.QueueTriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.pex.falaidoutor.model.dto.TriageAuthResponse;

@RestController
@RequestMapping("/api/triage")
@CrossOrigin(origins = "*")
public class QueueTriageController {
    @Autowired
    private QueueTriageService queueService;

    // Endpoint para autenticação
    @GetMapping("/login")
    public TriageAuthResponse authenticate(@RequestParam String cpf, @RequestParam String queueTicket) {
        return queueService.authenticate(cpf, queueTicket);
    }
}
