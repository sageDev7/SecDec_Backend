package com.gestiondeportiva.SAE_Backend.Persistencia;

import com.gestiondeportiva.SAE_Backend.Dominio.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInscripcionRepositorio extends JpaRepository<Inscripcion,Integer>{

}
