package com.gestiondeportiva.proyectoGestion.Persistencia;

import com.gestiondeportiva.proyectoGestion.Dominio.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface IAlumnoRepositorio extends JpaRepository<Alumno,Integer>{

}
