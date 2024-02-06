package com.gestiondeportiva.proyectoGestion.DTOs;

import lombok.Data;
import java.util.*;

@Data
public class AuthResponseDTO {
    private String username;
    private Integer id_u;
    private String token;
    private String tokenType = "Bearer ";
    private List<String> roles;

    public AuthResponseDTO (String accessToken, List<String> roles, String username, Integer id_u){
        this.username = username;
        this.token = accessToken;
        this.roles = roles;
        this.id_u = id_u;
    }
}
