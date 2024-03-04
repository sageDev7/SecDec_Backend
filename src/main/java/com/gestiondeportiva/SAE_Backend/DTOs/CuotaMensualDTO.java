package com.gestiondeportiva.SAE_Backend.DTOs;

import com.gestiondeportiva.SAE_Backend.Dominio.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class CuotaMensualDTO {
    private ClaveCuota claveCuota;
    private float importeCuota;
    private Integer alumno;
    private Integer disciplina;
    private Optional<PagoDeCuotaDTO> pdc;
}
