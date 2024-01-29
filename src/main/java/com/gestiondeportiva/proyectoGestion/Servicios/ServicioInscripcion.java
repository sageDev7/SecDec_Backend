package com.gestiondeportiva.proyectoGestion.Servicios;

import com.gestiondeportiva.proyectoGestion.DTOs.CuotaMensualDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.DisciplinaDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.InscripcionDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.ProfesorDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.*;
import com.gestiondeportiva.proyectoGestion.Mappers.DisciplinaMapper;
import com.gestiondeportiva.proyectoGestion.Mappers.InscripcionMapper;
import com.gestiondeportiva.proyectoGestion.Persistencia.*;
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

    public InscripcionDTO createOrUpdate(InscripcionDTO i){
        Inscripcion nuevaInscripcion = InscripcionMapper.DTOToEntity(i);

        for (CuotaMensualDTO cmDto : i.getCuotasMensuales()) {
            Optional<CuotaMensual> cm = cuotaMensualRepositorio.findById(cmDto.getClaveCuota());
            if (cm.isPresent()) {
                nuevaInscripcion.getCuotasMensuales().add(cm.get());
            }
        }

        Optional<Alumno> a = alumnoRepositorio.findById(i.getAlumno());
        if (a.isPresent()) {
            nuevaInscripcion.setAlumno(a.get());
        }

        Optional<Disciplina> d = disciplinaRepositorio.findById(i.getDisciplina());
        if (d.isPresent()) {
            nuevaInscripcion.setDisciplina(d.get());
        }

        Optional<Usuario> u = usuarioRepositorio.findById(i.getUsuario());
        if (u.isPresent()) {
            nuevaInscripcion.setUsuario(u.get());
        }

        return InscripcionMapper.entityToDTO(inscripcionRepositorio.save(nuevaInscripcion));
    }

    public List<InscripcionDTO> selectAll() {
        List<InscripcionDTO> liDto = new ArrayList<>();
        for (Inscripcion i: inscripcionRepositorio.findAll()) {
            liDto.add(InscripcionMapper.entityToDTO(i));
        }
        return liDto;
    }

    public InscripcionDTO selectById(Integer id_i) {
        return InscripcionMapper.entityToDTO(inscripcionRepositorio.findById(id_i).orElse(null));
    }

    public void delete(Integer id_i) {
        inscripcionRepositorio.deleteById(id_i);
    }

    // Otros métodos según sea necesario
}