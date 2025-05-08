package com.pex.falaidoutor.app.controller;

import com.pex.falaidoutor.domain.model.dto.FinalizedTriageDTO;
import com.pex.falaidoutor.domain.model.dto.TriageListDTO;
import com.pex.falaidoutor.app.service.QueueTriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pex.falaidoutor.domain.model.dto.TriageAuthResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/triages")
@CrossOrigin(origins = "*")
public class QueueTriageController {
    @Autowired
    private QueueTriageService queueService;

    @GetMapping()
    public ResponseEntity<List<TriageListDTO>> getFinalizedTriages() {
        List<TriageListDTO> triages = queueService.getFinalizedTriages();
        return ResponseEntity.ok(triages);
    }

    @GetMapping("/{queueId}")
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
