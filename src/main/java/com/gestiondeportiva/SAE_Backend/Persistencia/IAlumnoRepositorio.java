package com.gestiondeportiva.SAE_Backend.Persistencia;

import com.gestiondeportiva.SAE_Backend.Dominio.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlumnoRepositorio extends JpaRepository<Alumno,Integer>{

}
