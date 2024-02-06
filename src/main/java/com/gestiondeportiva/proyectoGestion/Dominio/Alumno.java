package com.gestiondeportiva.proyectoGestion.Dominio;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "Alumnos")
@Data
@NoArgsConstructor
public class Alumno extends Persona {
    @Enumerated(EnumType.STRING)
    @Column
    private TipoAlumno tipoAlumno;
    @Enumerated(EnumType.STRING)
    @Column
    private TipoSeguro tipoSeguro;
    @OneToMany(mappedBy = "alumno")
    private List<CuotaMensual> cuotasMensuales = new ArrayList<>();
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscripcion> inscripciones = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_a;
}