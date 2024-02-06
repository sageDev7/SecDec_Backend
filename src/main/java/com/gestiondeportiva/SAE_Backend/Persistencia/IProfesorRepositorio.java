package com.gestiondeportiva.proyectoGestion.Persistencia;

import com.gestiondeportiva.proyectoGestion.Dominio.Disciplina;
import com.gestiondeportiva.proyectoGestion.Dominio.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface IProfesorRepositorio extends JpaRepository<Profesor,Integer> {

}
