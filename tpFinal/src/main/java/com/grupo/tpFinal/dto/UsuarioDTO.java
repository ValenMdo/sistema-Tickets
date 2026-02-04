package com.grupo.tpFinal.dto;

import com.grupo.tpFinal.enums.Rol;
import com.grupo.tpFinal.model.Usuario;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private Rol rol;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.rol = usuario.getRol() != null
                ? usuario.getRol()
                : null;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }
}
