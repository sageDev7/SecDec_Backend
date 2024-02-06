package com.gestiondeportiva.proyectoGestion.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestiondeportiva.proyectoGestion.Dominio.Inscripcion;
import com.gestiondeportiva.proyectoGestion.Dominio.PagoDeCuota;
import com.gestiondeportiva.proyectoGestion.Dominio.Rol;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id_u;
    private String username;
    private List<Integer> pagosDeCuotas = new ArrayList<>();
    private List<Integer> inscripciones = new ArrayList<>();
    private List<RolDTO> roles = new ArrayList<>();
}
