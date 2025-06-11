package com.pex.falaidoutor.app.controller;

import com.pex.falaidoutor.domain.entity.QueueTriage;
import com.pex.falaidoutor.domain.model.FinalizedTriageDTO;
import com.pex.falaidoutor.domain.model.TriageListDTO;
import com.pex.falaidoutor.app.service.QueueTriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/triages")
@CrossOrigin(origins = "*")
public class QueueTriageController {
    @Autowired private QueueTriageService queueService;

    @PostMapping
    public ResponseEntity<QueueTriage> create(@RequestBody QueueTriage queueTriage) {
        QueueTriage created = queueService.createQueueTriage(queueTriage);
        return ResponseEntity.ok(created);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        queueService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
