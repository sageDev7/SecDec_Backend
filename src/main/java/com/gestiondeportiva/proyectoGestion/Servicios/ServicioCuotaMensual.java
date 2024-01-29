package com.gestiondeportiva.proyectoGestion.Servicios;

import com.gestiondeportiva.proyectoGestion.DTOs.AlumnoDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.CuotaMensualDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.InscripcionDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.*;
import com.gestiondeportiva.proyectoGestion.Mappers.AlumnoMapper;
import com.gestiondeportiva.proyectoGestion.Mappers.CuotaMensualMapper;
import com.gestiondeportiva.proyectoGestion.Persistencia.IAlumnoRepositorio;
import com.gestiondeportiva.proyectoGestion.Persistencia.ICuotaMensualRepositorio;
import com.gestiondeportiva.proyectoGestion.Persistencia.IDisciplinaRepositorio;
import com.gestiondeportiva.proyectoGestion.Persistencia.IInscripcionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioCuotaMensual {

    private final ICuotaMensualRepositorio cuotaMensualRepositorio;
    private final IAlumnoRepositorio alumnoRepositorio;
    private final IDisciplinaRepositorio disciplinaRepositorio;
    private final IInscripcionRepositorio inscripcionRepositorio;

    @Autowired
    public ServicioCuotaMensual(ICuotaMensualRepositorio cuotaMensualRepositorio, IAlumnoRepositorio alumnoRepositorio, IDisciplinaRepositorio disciplinaRepositorio,IInscripcionRepositorio inscripcionRepositorio) {
        this.cuotaMensualRepositorio = cuotaMensualRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
        this.disciplinaRepositorio = disciplinaRepositorio;
        this.inscripcionRepositorio = inscripcionRepositorio;

    }

    public CuotaMensualDTO createOrUpdate(CuotaMensualDTO cm){
        CuotaMensual nuevaCuota = CuotaMensualMapper.DTOToEntity(cm);

        Optional<Alumno> a = alumnoRepositorio.findById(cm.getAlumno());
        if (a.isPresent()) {
            nuevaCuota.setAlumno(a.get());
        }

        Optional<Disciplina> d = disciplinaRepositorio.findById(cm.getDisciplina());
        if (d.isPresent()) {
            nuevaCuota.setDisciplina(d.get());
        }

        Optional<Inscripcion> i = inscripcionRepositorio.findById(cm.getClaveCuota().getIcod());
        if (i.isPresent()) {
            nuevaCuota.setInscripcion(i.get());
        }

        return CuotaMensualMapper.entityToDTO(cuotaMensualRepositorio.save(nuevaCuota));
    }

    public List<CuotaMensualDTO> selectAll() {
        List<CuotaMensualDTO> lcDto = new ArrayList<>();
        for (CuotaMensual cm: cuotaMensualRepositorio.findAll()) {
            lcDto.add(CuotaMensualMapper.entityToDTO(cm));
        }
        return lcDto;
    }

    public CuotaMensualDTO selectById(ClaveCuota cc) {
        return CuotaMensualMapper.entityToDTO(cuotaMensualRepositorio.findById(cc).orElse(null));
    }

    public void delete(ClaveCuota cc) {
        cuotaMensualRepositorio.deleteById(cc);
    }

    // Otros métodos según sea necesario
}