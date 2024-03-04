package com.gestiondeportiva.SAE_Backend.Dominio;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class ClaveCuota implements Serializable {
    private int anio;
    private int mes;
    private Integer icod;
}
