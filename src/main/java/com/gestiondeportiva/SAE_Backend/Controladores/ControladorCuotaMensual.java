package com.gestiondeportiva.SAE_Backend.Controladores;

import com.gestiondeportiva.SAE_Backend.DTOs.CuotaMensualDTO;
import com.gestiondeportiva.SAE_Backend.Dominio.ClaveCuota;
import com.gestiondeportiva.SAE_Backend.Servicios.ServicioCuotaMensual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cuotas")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorCuotaMensual {

    // Inyección de dependencias
    private final ServicioCuotaMensual servicioCuotaMensual;

    @Autowired
    public ControladorCuotaMensual(ServicioCuotaMensual servicioCuotaMensual) {
        this.servicioCuotaMensual = servicioCuotaMensual;
    }

    // Endpoint para crear o actualizar una cuota mensual
    @PostMapping("/crear")
    public ResponseEntity<CuotaMensualDTO> createOrUpdateCuotaMensual(@Valid @RequestBody CuotaMensualDTO cuotaMensual) {
        // Llamada al servicio para crear o actualizar la cuota mensual
        CuotaMensualDTO nuevaCuotaMensual = servicioCuotaMensual.createOrUpdate(cuotaMensual);
        // Respuesta con la nueva cuota mensual y estado HTTP 201 (CREATED)
        return new ResponseEntity<>(nuevaCuotaMensual, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las cuotas mensuales
    @GetMapping("/ver")
    public List<CuotaMensualDTO> getAllCuotasMensuales() {
        // Llamada al servicio para obtener todas las cuotas mensuales
        return servicioCuotaMensual.selectAll();
    }

    // Endpoint para obtener una cuota mensual por su ID
    @GetMapping("/verId/{anio}/{mes}/{inscr}")
    public ResponseEntity<CuotaMensualDTO> getCuotaMensualById(@PathVariable int anio, @PathVariable int mes, @PathVariable Integer inscr) {
        // Crear la clave de cuota a partir de los parámetros de la URL
        ClaveCuota claveCuota = new ClaveCuota(anio, mes, inscr);
        // Llamada al servicio para obtener una cuota mensual por su ID
        CuotaMensualDTO cuotaMensual = servicioCuotaMensual.selectById(claveCuota);

        if (cuotaMensual != null) {
            // Respuesta con la cuota mensual y estado HTTP 200 (OK) si se encuentra
            return new ResponseEntity<>(cuotaMensual, HttpStatus.OK);
        } else {
            // Respuesta con estado HTTP 404 (NOT FOUND) si no se encuentra la cuota mensual
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una cuota mensual por su ID
    @DeleteMapping("/eliminar/{anio}/{mes}/{inscr}")
    public ResponseEntity<Void> deleteCuotaMensual(@PathVariable int anio, @PathVariable int mes, @PathVariable Integer inscr) {
        // Crear la clave de cuota a partir de los parámetros de la URL
        ClaveCuota claveCuota = new ClaveCuota(anio, mes, inscr);

        try {
            // Intento de eliminación llamando al servicio
            servicioCuotaMensual.delete(claveCuota);
            // Respuesta con estado HTTP 204 (NO CONTENT) si la eliminación es exitosa
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Respuesta con estado HTTP 500 (INTERNAL SERVER ERROR) si hay una excepción durante la eliminación
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
