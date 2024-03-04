package com.gestiondeportiva.SAE_Backend.Persistencia;

import com.gestiondeportiva.SAE_Backend.Dominio.PagoDeCuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagoDeCuotaRepositorio extends JpaRepository<PagoDeCuota,Integer> {

}
