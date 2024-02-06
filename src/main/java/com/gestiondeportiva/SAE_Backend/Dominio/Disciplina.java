package com.gestiondeportiva.proyectoGestion.Dominio;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "Disciplinas")
@Data
@NoArgsConstructor
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_d;
    @Column(name = "dnombre")
    private String nombre;
    @Column(name = "dimporte")
    private float importe;
    @Column(name = "dimportecuota")
    private float importeCuota;
    @OneToMany(mappedBy = "disciplina",cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones = new ArrayList<>();
    @OneToMany(mappedBy = "disciplina",cascade = CascadeType.ALL)
    private List<CuotaMensual> cuotasMensuales = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Profesores_Disciplinas",
            joinColumns = @JoinColumn(name = "id_d"),
            inverseJoinColumns = @JoinColumn(name = "id_p"))
    private List<Profesor> profesores = new ArrayList<>();

}
