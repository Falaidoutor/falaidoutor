package com.pex.falaidoutor.app.controller;

import com.pex.falaidoutor.app.service.TriageService;
import com.pex.falaidoutor.domain.entity.Triage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "/triage")
@CrossOrigin(origins = "*")
public class TriageController {

    @Autowired private TriageService triageService;

    @PostMapping(value = "/chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Triage> triageChat(@RequestBody Map<String, String> request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(triageService.createTriage(request));
    }

    @PostMapping(value = "/mock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Triage> triageChatMock(@RequestBody Map<String, String> request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(triageService.processRequest(request));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Triage>> listTriages() {
        return ResponseEntity.ok(triageService.getAllTriages());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Triage> getTriage(@PathVariable Long id) {
        Triage triage = triageService.getTriageById(id);
        return triage != null ? ResponseEntity.ok(triage) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTriage(@PathVariable Long id) {
        boolean deleted = triageService.deleteTriage(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

