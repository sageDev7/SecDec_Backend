package com.gestiondeportiva.SAE_Backend.Servicios;

import com.gestiondeportiva.SAE_Backend.DTOs.*;
import com.gestiondeportiva.SAE_Backend.Dominio.*;
import com.gestiondeportiva.SAE_Backend.Mappers.*;
import com.gestiondeportiva.SAE_Backend.Persistencia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioProfesor {

    @Autowired
    private IProfesorRepositorio profesorRepositorio;
    @Autowired
    private IDisciplinaRepositorio disciplinaRepositorio;

    // Crea o actualiza un profesor en la base de datos.
    public ProfesorDTO createOrUpdate(ProfesorDTO p){
        Profesor nuevoProfesor = ProfesorMapper.DTOToEntity(p);

        // Asociar disciplinas existentes al nuevo profesor.
        for (Integer disc : p.getDisciplinas()) {
            Optional<Disciplina> d = disciplinaRepositorio.findById(disc);
            if (d.isPresent()) {
                nuevoProfesor.getDisciplinas().add(d.get());
            }
        }
        return ProfesorMapper.entityToDTO(profesorRepositorio.save(nuevoProfesor));
    }

    // Obtiene todos los profesores de la base de datos.
    public List<ProfesorDTO> selectAll() {
        List<ProfesorDTO> lpDto = new ArrayList<>();
        for (Profesor p: profesorRepositorio.findAll()) {
            lpDto.add(ProfesorMapper.entityToDTO(p));
        }
        return lpDto;
    }

    // Obtiene un profesor por su ID.
    public ProfesorDTO selectById(Integer id_p) {
        return ProfesorMapper.entityToDTO(profesorRepositorio.findById(id_p).orElse(null));
    }

    // Elimina un profesor de la base de datos por su ID.
    public void delete(Integer id_p) {
        profesorRepositorio.deleteById(id_p);
    }
}
