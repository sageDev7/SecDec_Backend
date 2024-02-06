package com.gestiondeportiva.proyectoGestion.DTOs;

import com.gestiondeportiva.proyectoGestion.Dominio.Disciplina;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfesorDTO {
    private String nomyApe;
    private int dni;
    private String domicilio;
    private String localidad;
    private String provincia;
    private String tel;
    private Date fecNac;
    private String sexo;
    private String observaciones;
    private Integer id_p;
    private Date fecIng;
    private List<Integer> disciplinas  = new ArrayList<>();
}
