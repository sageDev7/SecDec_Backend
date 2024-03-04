package com.gestiondeportiva.SAE_Backend.Persistencia;

import com.gestiondeportiva.SAE_Backend.Dominio.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepositorio extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByNombre(String nombre);
}
