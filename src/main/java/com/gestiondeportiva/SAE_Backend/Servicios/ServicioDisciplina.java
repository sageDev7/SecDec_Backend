package com.gestiondeportiva.SAE_Backend.Servicios;

import com.gestiondeportiva.SAE_Backend.DTOs.*;
import com.gestiondeportiva.SAE_Backend.Dominio.*;
import com.gestiondeportiva.SAE_Backend.Mappers.DisciplinaMapper;
import com.gestiondeportiva.SAE_Backend.Persistencia.*;
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

    // Crea o actualiza una disciplina en la base de datos.
    public DisciplinaDTO createOrUpdate(DisciplinaDTO d){
        Disciplina nuevaDisciplina = DisciplinaMapper.DTOToEntity(d);

        // Asociar inscripciones existentes a la nueva disciplina.
        for (InscripcionDTO iDto : d.getInscripciones()) {
            Optional<Inscripcion> i = inscripcionRepositorio.findById(iDto.getIcod());
            if (i.isPresent()) {
                nuevaDisciplina.getInscripciones().add(i.get());
            }
        }

        // Asociar cuotas mensuales existentes a la nueva disciplina.
        for (ClaveCuota cc : d.getCuotasMensuales()) {
            Optional<CuotaMensual> cm = cuotaMensualRepositorio.findById(cc);
            if (cm.isPresent()) {
                nuevaDisciplina.getCuotasMensuales().add(cm.get());
            }
        }

        // Asociar profesores existentes a la nueva disciplina.
        for (ProfesorDTO pDto : d.getProfesores()) {
            Optional<Profesor> p = profesorRepositorio.findById(pDto.getId_p());
            if (p.isPresent()) {
                nuevaDisciplina.getProfesores().add(p.get());
            }
        }

        return DisciplinaMapper.entityToDTO(disciplinaRepositorio.save(nuevaDisciplina));
    }

    // Obtiene todas las disciplinas de la base de datos.
    public List<DisciplinaDTO> selectAll() {
        List<DisciplinaDTO> ldDto = new ArrayList<>();
        for (Disciplina d: disciplinaRepositorio.findAll()) {
            ldDto.add(DisciplinaMapper.entityToDTO(d));
        }
        return ldDto;
    }

    // Obtiene una disciplina por su ID.
    public DisciplinaDTO selectById(Integer id_d) {
        return DisciplinaMapper.entityToDTO(disciplinaRepositorio.findById(id_d).orElse(null));
    }

    // Elimina una disciplina de la base de datos por su ID.
    public void delete(Integer id_d) {
        disciplinaRepositorio.deleteById(id_d);
    }
}
