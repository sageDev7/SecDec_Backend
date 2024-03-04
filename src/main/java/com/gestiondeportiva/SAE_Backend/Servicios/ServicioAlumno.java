package com.gestiondeportiva.SAE_Backend.Servicios;

import com.gestiondeportiva.SAE_Backend.DTOs.AlumnoDTO;
import com.gestiondeportiva.SAE_Backend.DTOs.InscripcionDTO;
import com.gestiondeportiva.SAE_Backend.Dominio.Alumno;
import com.gestiondeportiva.SAE_Backend.Dominio.ClaveCuota;
import com.gestiondeportiva.SAE_Backend.Dominio.CuotaMensual;
import com.gestiondeportiva.SAE_Backend.Dominio.Inscripcion;
import com.gestiondeportiva.SAE_Backend.Mappers.AlumnoMapper;
import com.gestiondeportiva.SAE_Backend.Persistencia.IAlumnoRepositorio;
import com.gestiondeportiva.SAE_Backend.Persistencia.ICuotaMensualRepositorio;
import com.gestiondeportiva.SAE_Backend.Persistencia.IInscripcionRepositorio;
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

    // Crea o actualiza un alumno en la base de datos.
    public AlumnoDTO createOrUpdate(AlumnoDTO a){
        // Mapear el DTO a la entidad Alumno
        Alumno nuevoAlumno = AlumnoMapper.DTOToEntity(a);

        // Asociar inscripciones al nuevo alumno
        for (InscripcionDTO iDto : a.getInscripciones()) {
            Optional<Inscripcion> i = inscripcionRepositorio.findById(iDto.getIcod());
            if (i.isPresent()) {
                nuevoAlumno.getInscripciones().add(i.get());
            }
        }

        // Asociar cuotas mensuales al nuevo alumno
        for (ClaveCuota cc : a.getCuotasMensuales()) {
            Optional<CuotaMensual> cm = cuotaMensualRepositorio.findById(cc);
            if (cm.isPresent()) {
                nuevoAlumno.getCuotasMensuales().add(cm.get());
            }
        }

        // Guardar el nuevo alumno en la base de datos y mapear la entidad resultante a DTO
        return AlumnoMapper.entityToDTO(alumnoRepositorio.save(nuevoAlumno));
    }

    // Obtiene todos los alumnos de la base de datos.
    public List<AlumnoDTO> selectAll() {
        List<AlumnoDTO> laDto = new ArrayList<>();

        // Mapear cada entidad Alumno a un DTO y agregarlo a la lista
        for (Alumno a: alumnoRepositorio.findAll()) {
            laDto.add(AlumnoMapper.entityToDTO(a));
        }

        return laDto;
    }

    // Obtiene un alumno por su ID.
    public AlumnoDTO selectById(Integer id_a) {
        // Mapear la entidad Alumno a DTO y devolverlo
        return AlumnoMapper.entityToDTO(alumnoRepositorio.findById(id_a).orElse(null));
    }

    // Elimina un alumno de la base de datos por su ID.
    public void delete(Integer id_a) {
        // Eliminar el alumno por su ID
        alumnoRepositorio.deleteById(id_a);
    }
}