package com.gestiondeportiva.proyectoGestion.Servicios;

import com.gestiondeportiva.proyectoGestion.DTOs.AlumnoDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.InscripcionDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.Alumno;
import com.gestiondeportiva.proyectoGestion.Dominio.ClaveCuota;
import com.gestiondeportiva.proyectoGestion.Dominio.CuotaMensual;
import com.gestiondeportiva.proyectoGestion.Dominio.Inscripcion;
import com.gestiondeportiva.proyectoGestion.Mappers.AlumnoMapper;
import com.gestiondeportiva.proyectoGestion.Persistencia.IAlumnoRepositorio;
import com.gestiondeportiva.proyectoGestion.Persistencia.ICuotaMensualRepositorio;
import com.gestiondeportiva.proyectoGestion.Persistencia.IInscripcionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioAlumno {

    private final IAlumnoRepositorio alumnoRepositorio;
    private final IInscripcionRepositorio inscripcionRepositorio;
    private final ICuotaMensualRepositorio cuotaMensualRepositorio;

    @Autowired
    public ServicioAlumno(IAlumnoRepositorio alumnoRepositorio, IInscripcionRepositorio inscripcionRepositorio, ICuotaMensualRepositorio cuotaMensualRepositorio) {
        this.alumnoRepositorio = alumnoRepositorio;
        this.inscripcionRepositorio = inscripcionRepositorio;
        this.cuotaMensualRepositorio = cuotaMensualRepositorio;
    }

    public AlumnoDTO createOrUpdate(AlumnoDTO a){
        Alumno nuevoAlumno = AlumnoMapper.DTOToEntity(a);
        for (InscripcionDTO iDto : a.getInscripciones()) {
            Optional<Inscripcion> i = inscripcionRepositorio.findById(iDto.getIcod());
            if (i.isPresent()) {
                nuevoAlumno.getInscripciones().add(i.get());
            }
        }
        for (ClaveCuota cc : a.getCuotasMensuales()) {
            Optional<CuotaMensual> cm = cuotaMensualRepositorio.findById(cc);
            if (cm.isPresent()) {
                nuevoAlumno.getCuotasMensuales().add(cm.get());
            }
        }
        return AlumnoMapper.entityToDTO(alumnoRepositorio.save(nuevoAlumno));
    }

    public List<AlumnoDTO> selectAll() {
        List<AlumnoDTO> laDto = new ArrayList<>();
        for (Alumno a: alumnoRepositorio.findAll()) {
            laDto.add(AlumnoMapper.entityToDTO(a));
        }
        return laDto;
    }

    public AlumnoDTO selectById(Integer id_a) {
        return AlumnoMapper.entityToDTO(alumnoRepositorio.findById(id_a).orElse(null));
    }

    public void delete(Integer id_a) {
        alumnoRepositorio.deleteById(id_a);
    }

    // Otros métodos según sea necesario
}