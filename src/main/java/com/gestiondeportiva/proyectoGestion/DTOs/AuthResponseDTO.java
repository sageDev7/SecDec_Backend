package com.gestiondeportiva.proyectoGestion.DTOs;

import com.gestiondeportiva.proyectoGestion.Dominio.Rol;
import lombok.Data;
import java.util.*;

@Data
public class AuthResponseDTO {
    private String username;
    private String token;
    private String tokenType = "Bearer ";
    private List<String> roles;

    public AuthResponseDTO (String accessToken, List<String> roles, String username){
        this.username = username;
        this.token = accessToken;
        this.roles = roles;
    }
}
