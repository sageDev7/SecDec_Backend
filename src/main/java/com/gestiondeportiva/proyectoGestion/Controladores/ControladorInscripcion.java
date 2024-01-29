package com.gestiondeportiva.proyectoGestion.Controladores;

import com.gestiondeportiva.proyectoGestion.DTOs.InscripcionDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.Inscripcion;
import com.gestiondeportiva.proyectoGestion.Servicios.ServicioInscripcion;
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
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorInscripcion {

    private final ServicioInscripcion servicioInscripcion;

    @Autowired
    public ControladorInscripcion(ServicioInscripcion servicioInscripcion) {
        this.servicioInscripcion = servicioInscripcion;
    }

    @PostMapping("/crear")
    public ResponseEntity<InscripcionDTO> createOrUpdateInscripcion(@Valid @RequestBody InscripcionDTO inscripcion) {
        InscripcionDTO nuevaInscripcion = servicioInscripcion.createOrUpdate(inscripcion);
        //Validación de inscripcion vigente
        return new ResponseEntity<>(nuevaInscripcion, HttpStatus.CREATED);
    }

    @GetMapping("/ver")
    public List<InscripcionDTO> getAllInscripciones() {
        return servicioInscripcion.selectAll();
    }

    @GetMapping("/verId/{id}")
    public ResponseEntity<InscripcionDTO> getInscripcionById(@PathVariable Integer id) {
        InscripcionDTO inscripcion = servicioInscripcion.selectById(id);

        if (inscripcion != null) {
            return new ResponseEntity<>(inscripcion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Integer id) {
        try {
            servicioInscripcion.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Otros métodos según sea necesario
}