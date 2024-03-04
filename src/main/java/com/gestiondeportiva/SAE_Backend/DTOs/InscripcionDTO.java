package com.gestiondeportiva.SAE_Backend.DTOs;

import com.gestiondeportiva.SAE_Backend.Dominio.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class InscripcionDTO {
    private Integer icod;
    private Date fecInsc;
    private Date fecVenc;
    private float importeUnico;
    private float importeCuotas;
    private TipoInscripcion tipoInscr;
    private Integer alumno;
    private String alumnoNombre;
    private String disciplinaNombre;
    private Integer disciplina;
    private String usuarioNombre;
    private List<CuotaMensualDTO> cuotasMensuales = new ArrayList<>();
}
