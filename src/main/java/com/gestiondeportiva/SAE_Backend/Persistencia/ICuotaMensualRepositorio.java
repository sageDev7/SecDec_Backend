package com.gestiondeportiva.SAE_Backend.Persistencia;

import com.gestiondeportiva.SAE_Backend.Dominio.ClaveCuota;
import com.gestiondeportiva.SAE_Backend.Dominio.CuotaMensual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuotaMensualRepositorio extends JpaRepository<CuotaMensual, ClaveCuota>{

}
