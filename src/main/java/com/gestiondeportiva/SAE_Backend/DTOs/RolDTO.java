package com.gestiondeportiva.SAE_Backend.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RolDTO {
    private Integer id_r;
    private String nombre;
    private List<Integer> usuarios = new ArrayList<>();
}
