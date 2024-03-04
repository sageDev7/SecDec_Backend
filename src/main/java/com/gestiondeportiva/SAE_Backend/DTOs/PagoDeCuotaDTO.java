package com.gestiondeportiva.SAE_Backend.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
public class PagoDeCuotaDTO {
    private Integer nroPago;
    private Date fecha;
    private Time hora;
    private float importe;
    private String usuarioNombre;
}
