package com.gestiondeportiva.proyectoGestion.Controladores;

import com.gestiondeportiva.proyectoGestion.DTOs.AlumnoDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.Alumno;
import com.gestiondeportiva.proyectoGestion.Dominio.CuotaMensual;
import com.gestiondeportiva.proyectoGestion.Dominio.Inscripcion;
import com.gestiondeportiva.proyectoGestion.Servicios.ServicioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAlumno {

    private final ServicioAlumno servicioAlumno;

    @Autowired
    public ControladorAlumno(ServicioAlumno servicioAlumno) {
        this.servicioAlumno = servicioAlumno;
    }

    @PostMapping("/crear")
    public ResponseEntity<AlumnoDTO> createOrUpdateAlumno(@Valid @RequestBody AlumnoDTO alumno) {
        AlumnoDTO nuevoAlumno = servicioAlumno.createOrUpdate(alumno);
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
    }

    @GetMapping("/ver")
    public List<AlumnoDTO> getAllAlumnos() {
        return servicioAlumno.selectAll();
    }

    @GetMapping("/verId/{id}")
    public ResponseEntity<AlumnoDTO> getAlumnoById(@PathVariable @Min(1) Integer id) {
        AlumnoDTO alumno = servicioAlumno.selectById(id);
        if (alumno != null) {
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable @Min(1) Integer id) {
        try {
            servicioAlumno.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Otros métodos según sea necesario

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(javax.validation.ConstraintViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}