package com.gestiondeportiva.proyectoGestion.Controladores;

import com.gestiondeportiva.proyectoGestion.DTOs.ProfesorDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.Disciplina;
import com.gestiondeportiva.proyectoGestion.Dominio.Profesor;
import com.gestiondeportiva.proyectoGestion.Servicios.ServicioDisciplina;
import com.gestiondeportiva.proyectoGestion.Servicios.ServicioProfesor;
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
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorProfesor {
    private final ServicioProfesor servicioProfesor;
    private final ServicioDisciplina servicioDisciplina;

    @Autowired
    public ControladorProfesor(ServicioProfesor servicioProfesor, ServicioDisciplina servicioDisciplina) {
        this.servicioProfesor = servicioProfesor;
        this.servicioDisciplina = servicioDisciplina;
    }

    @PostMapping("/crear")
    public ResponseEntity<ProfesorDTO> createOrUpdateProfesor(@Valid @RequestBody ProfesorDTO profesor) {
        ProfesorDTO nuevoProfesor = servicioProfesor.createOrUpdate(profesor);
        return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
    }

    @GetMapping("/ver")
    public List<ProfesorDTO> getAllProfesores() {
        return servicioProfesor.selectAll();
    }

    @GetMapping("/verId/{id}")
    public ResponseEntity<ProfesorDTO> getProfesorById(@PathVariable Integer id) {
        ProfesorDTO profesor = servicioProfesor.selectById(id);

        if (profesor != null) {
            return new ResponseEntity<>(profesor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Integer id) {
        try {
            servicioProfesor.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Otros métodos según sea necesario
}
