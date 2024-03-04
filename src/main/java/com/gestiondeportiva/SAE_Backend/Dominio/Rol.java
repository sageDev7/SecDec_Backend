package com.gestiondeportiva.SAE_Backend.Dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Table(name = "Roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_r;
    @Column
    private String nombre;
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios = new ArrayList<>();
}
