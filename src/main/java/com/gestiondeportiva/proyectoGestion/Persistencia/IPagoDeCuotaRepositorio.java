package com.gestiondeportiva.proyectoGestion.Persistencia;

import com.gestiondeportiva.proyectoGestion.Dominio.PagoDeCuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagoDeCuotaRepositorio extends JpaRepository<PagoDeCuota,Integer> {

}
