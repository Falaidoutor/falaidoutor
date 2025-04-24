package com.pex.falaidoutor.controller;

import com.pex.falaidoutor.model.dto.FinalizedTriageDTO;
import com.pex.falaidoutor.model.dto.TriageListDTO;
import com.pex.falaidoutor.model.entity.QueueTriage;
import com.pex.falaidoutor.service.QueueTriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pex.falaidoutor.model.dto.TriageAuthResponse;

import java.util.List;

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

    @GetMapping("/getTriages")
    public ResponseEntity<List<TriageListDTO>> getFinalizedTriages() {
        List<TriageListDTO> triages = queueService.getFinalizedTriages();
        return ResponseEntity.ok(triages);
    }

    @GetMapping("/getDetails/{queueId}")
    public ResponseEntity<FinalizedTriageDTO> getDetails(@PathVariable String queueId) {
        Long id = Long.parseLong(queueId);
        FinalizedTriageDTO queueTriage = queueService.getQueueTriageById(id).orElse(null);

        if (queueTriage != null) {
            return ResponseEntity.ok(queueTriage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
