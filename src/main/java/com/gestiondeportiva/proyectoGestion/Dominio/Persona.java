
package com.gestiondeportiva.proyectoGestion.Dominio;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Persona {
    private String nomyApe;
    private int dni;
    private String domicilio;
    private String localidad;
    private String provincia;
    private String tel;
    private Date fecNac;
    private String sexo;
    private String observaciones;

    public Persona(int dni){
        this.dni = dni;
        this.nomyApe = "";
        this.domicilio = "";
        this.localidad = "";
        this.provincia = "";
        this.tel = "";
        this.fecNac = null;
        this.sexo = "";
        this.observaciones = "";
    }

    public Persona(String nomyApe, int dni, String domicilio, String localidad, String provincia, String tel, Date fecNac, String sexo) {
        this.nomyApe = nomyApe;
        this.dni = dni;
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.provincia = provincia;
        this.tel = tel;
        this.fecNac = fecNac;
        this.sexo = sexo;
    }

}
