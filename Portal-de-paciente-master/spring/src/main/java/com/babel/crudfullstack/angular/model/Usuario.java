package com.babel.crudfullstack.angular.model;

import jakarta.persistence.*;

@Entity
@Table(name = "portal_usuario")
public class Usuario {
    @Id
    @Column(name = "Usuario", length = 9)
    private String usuario;

    @Column(name = "password", length = 20)
    private String password;

    public Usuario() {}

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}