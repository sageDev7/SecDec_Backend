package com.gestiondeportiva.proyectoGestion.Persistencia;

import com.gestiondeportiva.proyectoGestion.Dominio.ClaveCuota;
import com.gestiondeportiva.proyectoGestion.Dominio.CuotaMensual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuotaMensualRepositorio extends JpaRepository<CuotaMensual, ClaveCuota>{

}
