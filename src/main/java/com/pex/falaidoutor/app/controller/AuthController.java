package com.pex.falaidoutor.app.controller;

import com.pex.falaidoutor.app.service.AuthService;
import com.pex.falaidoutor.domain.model.dto.TriageAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired private AuthService authService;

    // Endpoint para autenticação
    @GetMapping()
    public TriageAuthResponse authenticate(@RequestParam String cpf, @RequestParam String queueTicket) {
        return authService.authenticate(cpf, queueTicket);
    }
}
