package com.gestiondeportiva.SAE_Backend.Controladores;

import com.gestiondeportiva.SAE_Backend.DTOs.InscripcionDTO;
import com.gestiondeportiva.SAE_Backend.Servicios.ServicioInscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/inscripciones")
@Validated
@CrossOrigin(origins = "http://localhost:4200/") // Configuración de origen permitido para peticiones
public class ControladorInscripcion {

    // Inyección de dependencias
    private final ServicioInscripcion servicioInscripcion;

    @Autowired
    public ControladorInscripcion(ServicioInscripcion servicioInscripcion) {
        this.servicioInscripcion = servicioInscripcion;
    }

    // Endpoint para crear o actualizar una inscripción
    @PostMapping("/crear")
    public ResponseEntity<InscripcionDTO> createOrUpdateInscripcion(@Valid @RequestBody InscripcionDTO inscripcion) {
        // Llamada al servicio para crear o actualizar la inscripción
        InscripcionDTO nuevaInscripcion = servicioInscripcion.createOrUpdate(inscripcion);
        // Validación de inscripción vigente (comentario añadido)
        return new ResponseEntity<>(nuevaInscripcion, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las inscripciones
    @GetMapping("/ver")
    public List<InscripcionDTO> getAllInscripciones() {
        // Llamada al servicio para obtener todas las inscripciones
        return servicioInscripcion.selectAll();
    }

    // Endpoint para obtener una inscripción por su ID
    @GetMapping("/verId/{id}")
    public ResponseEntity<InscripcionDTO> getInscripcionById(@PathVariable Integer id) {
        // Llamada al servicio para obtener una inscripción por su ID
        InscripcionDTO inscripcion = servicioInscripcion.selectById(id);

        if (inscripcion != null) {
            // Respuesta con la inscripción y estado HTTP 200 (OK) si se encuentra
            return new ResponseEntity<>(inscripcion, HttpStatus.OK);
        } else {
            // Respuesta con estado HTTP 404 (NOT FOUND) si no se encuentra la inscripción
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una inscripción por su ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Integer id) {
        try {
            // Intento de eliminación llamando al servicio
            servicioInscripcion.delete(id);
            // Respuesta con estado HTTP 204 (NO CONTENT) si la eliminación es exitosa
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Respuesta con estado HTTP 500 (INTERNAL SERVER ERROR) si hay una excepción durante la eliminación
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
