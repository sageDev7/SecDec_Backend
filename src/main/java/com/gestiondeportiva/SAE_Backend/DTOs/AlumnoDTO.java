package com.gestiondeportiva.SAE_Backend.DTOs;

import com.gestiondeportiva.SAE_Backend.Dominio.ClaveCuota;
import com.gestiondeportiva.SAE_Backend.Dominio.TipoAlumno;
import com.gestiondeportiva.SAE_Backend.Dominio.TipoSeguro;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AlumnoDTO {
    private String nomyApe;
    private int dni;
    private String domicilio;
    private String localidad;
    private String provincia;
    private String tel;
    private Date fecNac;
    private String sexo;
    private String observaciones;
    private TipoAlumno tipoAlumno;
    private TipoSeguro tipoSeguro;
    private List<ClaveCuota> cuotasMensuales = new ArrayList<>();
    private List<InscripcionDTO> inscripciones = new ArrayList<>();
    private Integer id_a;
}
