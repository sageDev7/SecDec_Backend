package com.gestiondeportiva.proyectoGestion.Dominio;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ClaveCuota implements Serializable {
    private int anio;
    private int mes;
    private Integer icod;

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public Integer getIcod() {
        return icod;
    }

    public void setIcod(Integer icod) {
        this.icod = icod;
    }
}
