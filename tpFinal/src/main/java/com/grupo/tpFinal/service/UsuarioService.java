package com.grupo.tpFinal.service;

import com.grupo.tpFinal.dto.LoginRequest;
import com.grupo.tpFinal.dto.PasswordDTO;
import com.grupo.tpFinal.model.Usuario;
import com.grupo.tpFinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;



    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // -------------------------------
    // GET /users/me
    // -------------------------------

    @GetMapping("/users/me")
    public Usuario getUsuarioActual() {
        // PROVISORIO (hasta tener auth real)
        return usuarioRepository.findById(4L)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // -------------------------------
    // PUT /users/change-password
    // -------------------------------

    public void cambiarPassword(PasswordDTO dto) {
       // Usuario usuario = getUsuarioActual();

        // PROVISORIO hasta tener auth real
        Usuario usuario = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(dto.getPasswordActual())) {
            throw new RuntimeException("La contraseña actual es incorrecta");
        }

        usuario.setPassword(dto.getPasswordNueva());
        usuarioRepository.save(usuario);
    }

    // -------------------------------
    // GET /users
    // -------------------------------

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }


    public Usuario login(LoginRequest request) {
        return usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

}
