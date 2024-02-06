package com.gestiondeportiva.proyectoGestion.Dominio;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Entity
@Table(name = "Inscripciones")
@Data
@NoArgsConstructor
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer icod;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecInsc;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecVenc;
    @Column
    private float importeUnico;
    @Enumerated(EnumType.STRING)
    @Column
    private TipoInscripcion tipoInscr;
    @ManyToOne
    private Alumno alumno = new Alumno();
    @ManyToOne
    private Disciplina disciplina = new Disciplina();
    @ManyToOne
    private Usuario usuario = new Usuario();
    @OneToMany(mappedBy = "inscripcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CuotaMensual> cuotasMensuales = new ArrayList<>();

}
