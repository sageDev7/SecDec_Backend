package com.gestiondeportiva.SAE_Backend.DTOs;

import com.gestiondeportiva.SAE_Backend.Dominio.ClaveCuota;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DisciplinaDTO {
    private Integer id_d;
    private String nombre;
    private float importe;
    private float importeCuota;
    private List<InscripcionDTO> inscripciones = new ArrayList<>();
    private List<ClaveCuota> cuotasMensuales = new ArrayList<>();
    private List<ProfesorDTO> profesores = new ArrayList<>();
}
