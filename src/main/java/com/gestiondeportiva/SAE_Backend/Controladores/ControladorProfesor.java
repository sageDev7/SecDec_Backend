package com.gestiondeportiva.SAE_Backend.Controladores;

import com.gestiondeportiva.SAE_Backend.DTOs.ProfesorDTO;
import com.gestiondeportiva.SAE_Backend.Servicios.ServicioDisciplina;
import com.gestiondeportiva.SAE_Backend.Servicios.ServicioProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profesores")
@Validated
@CrossOrigin(origins = "http://localhost:4200/") // Origen permitido para CORS
public class ControladorProfesor {

    // Inyección de dependencias
    private final ServicioProfesor servicioProfesor;
    private final ServicioDisciplina servicioDisciplina;

    @Autowired
    public ControladorProfesor(ServicioProfesor servicioProfesor, ServicioDisciplina servicioDisciplina) {
        this.servicioProfesor = servicioProfesor;
        this.servicioDisciplina = servicioDisciplina;
    }

    // Endpoint para crear o actualizar un profesor
    @PostMapping("/crear")
    public ResponseEntity<ProfesorDTO> createOrUpdateProfesor(@Valid @RequestBody ProfesorDTO profesor) {
        // Llamada al servicio para crear o actualizar el profesor
        ProfesorDTO nuevoProfesor = servicioProfesor.createOrUpdate(profesor);
        // Respuesta con el nuevo profesor creado y un estado HTTP 201 (CREATED)
        return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los profesores
    @GetMapping("/ver")
    public List<ProfesorDTO> getAllProfesores() {
        // Llamada al servicio para obtener todos los profesores
        return servicioProfesor.selectAll();
    }

    // Endpoint para obtener un profesor por su ID
    @GetMapping("/verId/{id}")
    public ResponseEntity<ProfesorDTO> getProfesorById(@PathVariable Integer id) {
        // Llamada al servicio para obtener un profesor por su ID
        ProfesorDTO profesor = servicioProfesor.selectById(id);

        if (profesor != null) {
            // Si se encuentra el profesor, se devuelve junto con un estado HTTP 200 (OK)
            return new ResponseEntity<>(profesor, HttpStatus.OK);
        } else {
            // Si el profesor no se encuentra, se devuelve un estado HTTP 404 (NOT FOUND)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un profesor por su ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Integer id) {
        try {
            // Se intenta eliminar el profesor llamando al servicio correspondiente
            servicioProfesor.delete(id);
            // Si la eliminación tiene éxito, se devuelve un estado HTTP 204 (NO CONTENT)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Si hay un error durante la eliminación, se devuelve un estado HTTP 500 (INTERNAL SERVER ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
