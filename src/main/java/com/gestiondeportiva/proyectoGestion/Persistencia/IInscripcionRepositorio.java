package com.gestiondeportiva.proyectoGestion.Persistencia;

import com.gestiondeportiva.proyectoGestion.Dominio.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInscripcionRepositorio extends JpaRepository<Inscripcion,Integer>{

}
