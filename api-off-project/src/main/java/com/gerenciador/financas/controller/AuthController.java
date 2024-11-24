package com.gerenciador.financas.controller;

import com.gerenciador.financas.dto.AuthenticationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping (value = "/login")
    public ResponseEntity<?> login (@RequestBody AuthenticationDTO authDTO) {

    }
}
