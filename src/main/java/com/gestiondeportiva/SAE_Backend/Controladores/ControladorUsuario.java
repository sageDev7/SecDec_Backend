package com.gestiondeportiva.SAE_Backend.Controladores;

import com.gestiondeportiva.SAE_Backend.DTOs.UsuarioDTO;
import com.gestiondeportiva.SAE_Backend.Servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorUsuario {

    // Inyección de dependencias
    private final ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    // Endpoint para crear o actualizar un usuario
    @PostMapping("/crear")
    public ResponseEntity<UsuarioDTO> createOrUpdateUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        // Llamada al servicio para crear o actualizar el usuario
        UsuarioDTO nuevoUsuario = servicioUsuario.createOrUpdate(usuario);
        // Se devuelve el nuevo usuario creado y se devuelve un estado HTTP 201 (CREATED)
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los usuarios
    @GetMapping("/ver")
    public List<UsuarioDTO> getAllUsuarios() {
        // Llamada al servicio para obtener todos los usuarios
        return servicioUsuario.selectAll();
    }

    // Endpoint para obtener un usuario por su ID
    @GetMapping("/verId/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Integer id) {
        // Llamada al servicio para obtener un usuario por su ID
        UsuarioDTO usuario = servicioUsuario.selectById(id);

        if (usuario != null) {
            // Si se encuentra el usuario, se devuelve junto con un estado HTTP 200 (OK)
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            // Si el usuario no se encuentra, se devuelve un estado HTTP 404 (NOT FOUND)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un usuario por su ID
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        try {
            // Se intenta eliminar el usuario llamando al servicio correspondiente
            servicioUsuario.delete(id);
            // Si la eliminación tiene éxito, se devuelve un estado HTTP 204 (NO CONTENT)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Si hay un error durante la eliminación, se devuelve un estado HTTP 500 (INTERNAL SERVER ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
