package com.grupo.tpFinal.controller;

import com.grupo.tpFinal.dto.LoginRequest;
import com.grupo.tpFinal.model.Usuario;
import com.grupo.tpFinal.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.AuthProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest request) {
        /* TODO: buscar usuario por email */
        return usuarioService.login(request);
    }

    @PostMapping("/logout")
    public void logout() {
        // placeholder
    }
}
