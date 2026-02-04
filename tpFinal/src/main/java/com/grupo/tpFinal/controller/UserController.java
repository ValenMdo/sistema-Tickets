package com.grupo.tpFinal.controller;

import com.grupo.tpFinal.dto.PasswordDTO;
import com.grupo.tpFinal.model.Usuario;
import com.grupo.tpFinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/test")
    public String test() {
        return "API OK";
    }


    @GetMapping("/me")
    public Usuario perfil() {
        return usuarioService.getUsuarioActual();
    }

    @PutMapping("/change-password")
    public void cambiarPassword(@RequestBody PasswordDTO dto) {
        usuarioService.cambiarPassword(dto);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerTodos();
    }
}
