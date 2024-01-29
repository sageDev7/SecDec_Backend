package com.gestiondeportiva.proyectoGestion.Persistencia;

import com.gestiondeportiva.proyectoGestion.Dominio.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDisciplinaRepositorio extends JpaRepository<Disciplina,Integer>{

}
