package com.gestiondeportiva.SAE_Backend.Controladores;

import com.gestiondeportiva.SAE_Backend.DTOs.AlumnoDTO;
import com.gestiondeportiva.SAE_Backend.Servicios.ServicioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAlumno {

    // Inyección de dependencias
    private final ServicioAlumno servicioAlumno;

    @Autowired
    public ControladorAlumno(ServicioAlumno servicioAlumno) {
        this.servicioAlumno = servicioAlumno;
    }

    // Endpoint para crear o actualizar un alumno
    @PostMapping("/crear")
    public ResponseEntity<AlumnoDTO> createOrUpdateAlumno(@Valid @RequestBody AlumnoDTO alumno) {
        // Llamada al servicio para crear o actualizar el alumno
        AlumnoDTO nuevoAlumno = servicioAlumno.createOrUpdate(alumno);
        // Respuesta con el nuevo alumno y estado HTTP 201 (CREATED)
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los alumnos
    @GetMapping("/ver")
    public List<AlumnoDTO> getAllAlumnos() {
        // Llamada al servicio para obtener todos los alumnos
        return servicioAlumno.selectAll();
    }

    // Endpoint para obtener un alumno por su ID
    @GetMapping("/verId/{id}")
    public ResponseEntity<AlumnoDTO> getAlumnoById(@PathVariable @Min(1) Integer id) {
        // Llamada al servicio para obtener un alumno por su ID
        AlumnoDTO alumno = servicioAlumno.selectById(id);
        if (alumno != null) {
            // Respuesta con el alumno y estado HTTP 200 (OK) si se encuentra
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        } else {
            // Respuesta con estado HTTP 404 (NOT FOUND) si no se encuentra el alumno
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un alumno por su ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable @Min(1) Integer id) {
        try {
            // Intento de eliminación llamando al servicio
            servicioAlumno.delete(id);
            // Respuesta con estado HTTP 204 (NO CONTENT) si la eliminación es exitosa
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Respuesta con estado HTTP 500 (INTERNAL SERVER ERROR) si hay una excepción durante la eliminación
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Manejo de excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Respuesta con el mensaje de la excepción y estado HTTP 500 (INTERNAL SERVER ERROR)
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Manejo de excepciones de validación
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(javax.validation.ConstraintViolationException e) {
        // Respuesta con el mensaje de la excepción y estado HTTP 400 (BAD REQUEST)
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
