package com.gestiondeportiva.proyectoGestion.Dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_u;
    @Column
    private String password;
    @Column
    private String username;
    @OneToMany(mappedBy = "usuario")
    private List<PagoDeCuota> pagosDeCuotas = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    private List<Inscripcion> inscripciones = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "UsuariosRoles",
            joinColumns = @JoinColumn(name = "id_u"),
            inverseJoinColumns = @JoinColumn(name = "id_r"))
    private List<Rol> roles = new ArrayList<>();
}
