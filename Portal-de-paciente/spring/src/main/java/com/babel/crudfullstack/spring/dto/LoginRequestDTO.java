package com.babel.crudfullstack.spring.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
    
    @NotBlank(message = "El usuario no puede estar vacío")
    private String usuario;
    
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    public LoginRequestDTO() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
