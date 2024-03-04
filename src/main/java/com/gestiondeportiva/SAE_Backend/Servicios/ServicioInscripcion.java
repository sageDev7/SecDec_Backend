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
public class ServicioInscripcion {

    private final IAlumnoRepositorio alumnoRepositorio;
    private final IInscripcionRepositorio inscripcionRepositorio;
    private final ICuotaMensualRepositorio cuotaMensualRepositorio;
    private final IDisciplinaRepositorio disciplinaRepositorio;
    private final IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    public ServicioInscripcion(IInscripcionRepositorio inscripcionRepositorio,IAlumnoRepositorio alumnoRepositorio,ICuotaMensualRepositorio cuotaMensualRepositorio,IDisciplinaRepositorio disciplinaRepositorio,IUsuarioRepositorio usuarioRepositorio) {
        this.inscripcionRepositorio = inscripcionRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
        this.cuotaMensualRepositorio = cuotaMensualRepositorio;
        this.disciplinaRepositorio = disciplinaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    // Crea o actualiza una inscripción en la base de datos.
    public InscripcionDTO createOrUpdate(InscripcionDTO i){
        Inscripcion nuevaInscripcion = InscripcionMapper.DTOToEntity(i);

        // Asociar cuotas mensuales existentes a la nueva inscripción.
        for (CuotaMensualDTO cmDto : i.getCuotasMensuales()) {
            Optional<CuotaMensual> cm = cuotaMensualRepositorio.findById(cmDto.getClaveCuota());
            if (cm.isPresent()) {
                nuevaInscripcion.getCuotasMensuales().add(cm.get());
            }
        }

        // Asociar alumno existente a la nueva inscripción.
        Optional<Alumno> a = alumnoRepositorio.findById(i.getAlumno());
        if (a.isPresent()) {
            nuevaInscripcion.setAlumno(a.get());
        }

        // Asociar disciplina existente a la nueva inscripción.
        Optional<Disciplina> d = disciplinaRepositorio.findById(i.getDisciplina());
        if (d.isPresent()) {
            nuevaInscripcion.setDisciplina(d.get());
        }

        // Asociar usuario existente a la nueva inscripción.
        Optional<Usuario> u = usuarioRepositorio.findByUsername(i.getUsuarioNombre());
        if (u.isPresent()) {
            nuevaInscripcion.setUsuario(u.get());
        }

        return InscripcionMapper.entityToDTO(inscripcionRepositorio.save(nuevaInscripcion));
    }

    // Obtiene todas las inscripciones de la base de datos.
    public List<InscripcionDTO> selectAll() {
        List<InscripcionDTO> liDto = new ArrayList<>();
        for (Inscripcion i: inscripcionRepositorio.findAll()) {
            liDto.add(InscripcionMapper.entityToDTO(i));
        }
        return liDto;
    }

    // Obtiene una inscripción por su ID.
    public InscripcionDTO selectById(Integer id_i) {
        return InscripcionMapper.entityToDTO(inscripcionRepositorio.findById(id_i).orElse(null));
    }

    // Elimina una inscripción de la base de datos por su ID.
    public void delete(Integer id_i) {
        inscripcionRepositorio.deleteById(id_i);
    }
}
