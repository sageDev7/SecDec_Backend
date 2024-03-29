package com.gestiondeportiva.SAE_Backend.Dominio;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "Profesores")
@Data
@NoArgsConstructor
public class Profesor extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_p;
    @Column
    private Date fecIng;
    @ManyToMany(mappedBy = "profesores",cascade = CascadeType.PERSIST)
    private List<Disciplina> disciplinas = new ArrayList<>();

}
