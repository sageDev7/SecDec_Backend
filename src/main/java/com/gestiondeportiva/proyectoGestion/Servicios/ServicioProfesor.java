package com.gestiondeportiva.proyectoGestion.Servicios;

import com.gestiondeportiva.proyectoGestion.DTOs.*;
import com.gestiondeportiva.proyectoGestion.Dominio.*;
import com.gestiondeportiva.proyectoGestion.Mappers.*;
import com.gestiondeportiva.proyectoGestion.Persistencia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioProfesor {

    @Autowired
    private  IProfesorRepositorio profesorRepositorio;
    @Autowired
    private  IDisciplinaRepositorio disciplinaRepositorio;


    public ProfesorDTO createOrUpdate(ProfesorDTO p){
        Profesor nuevoProfesor = ProfesorMapper.DTOToEntity(p);

        for (Integer disc : p.getDisciplinas()) {
            Optional<Disciplina> d = disciplinaRepositorio.findById(disc);
            if (d.isPresent()) {
                nuevoProfesor.getDisciplinas().add(d.get());
            }
        }
        return ProfesorMapper.entityToDTO(profesorRepositorio.save(nuevoProfesor));
    }

    public List<ProfesorDTO> selectAll() {
        List<ProfesorDTO> lpDto = new ArrayList<>();
        for (Profesor p: profesorRepositorio.findAll()) {
            lpDto.add(ProfesorMapper.entityToDTO(p));
        }
        return lpDto;
    }

    public ProfesorDTO selectById(Integer id_p) {
        return ProfesorMapper.entityToDTO(profesorRepositorio.findById(id_p).orElse(null));
    }

    public void delete(Integer id_p) {
        profesorRepositorio.deleteById(id_p);
    }

}