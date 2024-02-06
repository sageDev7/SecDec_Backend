package com.gestiondeportiva.proyectoGestion.Servicios;

import com.gestiondeportiva.proyectoGestion.DTOs.*;
import com.gestiondeportiva.proyectoGestion.Dominio.*;
import com.gestiondeportiva.proyectoGestion.Mappers.DisciplinaMapper;
import com.gestiondeportiva.proyectoGestion.Persistencia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioDisciplina {

    private final IDisciplinaRepositorio disciplinaRepositorio;
    private final IInscripcionRepositorio inscripcionRepositorio;
    private final ICuotaMensualRepositorio cuotaMensualRepositorio;
    private final IProfesorRepositorio profesorRepositorio;

    @Autowired
    public ServicioDisciplina(IDisciplinaRepositorio disciplinaRepositorio, IInscripcionRepositorio inscripcionRepositorio, ICuotaMensualRepositorio cuotaMensualRepositorio, IProfesorRepositorio profesorRepositorio) {
        this.disciplinaRepositorio = disciplinaRepositorio;
        this.inscripcionRepositorio = inscripcionRepositorio;
        this.cuotaMensualRepositorio = cuotaMensualRepositorio;
        this.profesorRepositorio = profesorRepositorio;

    }

    public DisciplinaDTO createOrUpdate(DisciplinaDTO d){
        Disciplina nuevaDisciplina = DisciplinaMapper.DTOToEntity(d);
        for (InscripcionDTO iDto : d.getInscripciones()) {
            Optional<Inscripcion> i = inscripcionRepositorio.findById(iDto.getIcod());
            if (i.isPresent()) {
                nuevaDisciplina.getInscripciones().add(i.get());
            }
        }
        for (ClaveCuota cc : d.getCuotasMensuales()) {
            Optional<CuotaMensual> cm = cuotaMensualRepositorio.findById(cc);
            if (cm.isPresent()) {
                nuevaDisciplina.getCuotasMensuales().add(cm.get());
            }
        }

        for (ProfesorDTO pDto : d.getProfesores()) {
            Optional<Profesor> p = profesorRepositorio.findById(pDto.getId_p());
            if (p.isPresent()) {
                nuevaDisciplina.getProfesores().add(p.get());
            }
        }

        return DisciplinaMapper.entityToDTO(disciplinaRepositorio.save(nuevaDisciplina));
    }

    public List<DisciplinaDTO> selectAll() {
        List<DisciplinaDTO> ldDto = new ArrayList<>();
        for (Disciplina d: disciplinaRepositorio.findAll()) {
            ldDto.add(DisciplinaMapper.entityToDTO(d));
        }
        return ldDto;
    }

    public DisciplinaDTO selectById(Integer id_d) {
        return DisciplinaMapper.entityToDTO(disciplinaRepositorio.findById(id_d).orElse(null));
    }

    public void delete(Integer id_d) {
        disciplinaRepositorio.deleteById(id_d);
    }

}