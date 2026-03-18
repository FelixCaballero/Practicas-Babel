package com.example.portal_paciente.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "portal_usuario")
public class portalUsuario {
    @Id
    @Column(name = "usuario",length = 9)
    private String usuario;

    @Column(length = 120)
    private String password;

}
