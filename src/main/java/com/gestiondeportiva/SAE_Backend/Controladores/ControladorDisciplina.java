package com.gestiondeportiva.proyectoGestion.Controladores;

import com.gestiondeportiva.proyectoGestion.DTOs.DisciplinaDTO;
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
@RequestMapping("/disciplinas")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorDisciplina {

    private final ServicioDisciplina servicioDisciplina;
    private final ServicioProfesor servicioProfesor;

    @Autowired
    public ControladorDisciplina(ServicioDisciplina servicioDisciplina,ServicioProfesor servicioProfesor) {
        this.servicioDisciplina = servicioDisciplina;
        this.servicioProfesor = servicioProfesor;
    }

    @PostMapping("/crear")
    public ResponseEntity<DisciplinaDTO> createOrUpdateDisciplina(@Valid @RequestBody DisciplinaDTO disciplina) {
        DisciplinaDTO nuevaDisciplina = servicioDisciplina.createOrUpdate(disciplina);
        return new ResponseEntity<>(nuevaDisciplina, HttpStatus.CREATED);
    }

    @GetMapping("/ver")
    public List<DisciplinaDTO> getAllDisciplinas() {
        return servicioDisciplina.selectAll();
    }

    @GetMapping("/verId/{id}")
    public ResponseEntity<DisciplinaDTO> getDisciplinaById(@PathVariable Integer id) {
        DisciplinaDTO disciplina = servicioDisciplina.selectById(id);

        if (disciplina != null) {
            return new ResponseEntity<>(disciplina, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Integer id) {
        try {
            servicioDisciplina.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}