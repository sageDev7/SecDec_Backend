package com.gestiondeportiva.SAE_Backend.Controladores;

import com.gestiondeportiva.SAE_Backend.DTOs.DisciplinaDTO;
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
@RequestMapping("/disciplinas")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorDisciplina {

    // Inyección de dependencias
    private final ServicioDisciplina servicioDisciplina;
    private final ServicioProfesor servicioProfesor;

    @Autowired
    public ControladorDisciplina(ServicioDisciplina servicioDisciplina, ServicioProfesor servicioProfesor) {
        this.servicioDisciplina = servicioDisciplina;
        this.servicioProfesor = servicioProfesor;
    }

    // Endpoint para crear o actualizar una disciplina
    @PostMapping("/crear")
    public ResponseEntity<DisciplinaDTO> createOrUpdateDisciplina(@Valid @RequestBody DisciplinaDTO disciplina) {
        // Llamada al servicio para crear o actualizar la disciplina
        DisciplinaDTO nuevaDisciplina = servicioDisciplina.createOrUpdate(disciplina);
        // Respuesta con la nueva disciplina y estado HTTP 201 (CREATED)
        return new ResponseEntity<>(nuevaDisciplina, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las disciplinas
    @GetMapping("/ver")
    public List<DisciplinaDTO> getAllDisciplinas() {
        // Llamada al servicio para obtener todas las disciplinas
        return servicioDisciplina.selectAll();
    }

    // Endpoint para obtener una disciplina por su ID
    @GetMapping("/verId/{id}")
    public ResponseEntity<DisciplinaDTO> getDisciplinaById(@PathVariable Integer id) {
        // Llamada al servicio para obtener una disciplina por su ID
        DisciplinaDTO disciplina = servicioDisciplina.selectById(id);

        if (disciplina != null) {
            // Respuesta con la disciplina y estado HTTP 200 (OK) si se encuentra
            return new ResponseEntity<>(disciplina, HttpStatus.OK);
        } else {
            // Respuesta con estado HTTP 404 (NOT FOUND) si no se encuentra la disciplina
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una disciplina por su ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Integer id) {
        try {
            // Intento de eliminación llamando al servicio
            servicioDisciplina.delete(id);
            // Respuesta con estado HTTP 204 (NO CONTENT) si la eliminación es exitosa
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Respuesta con estado HTTP 500 (INTERNAL SERVER ERROR) si hay una excepción durante la eliminación
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
