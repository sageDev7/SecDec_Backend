package com.gestiondeportiva.proyectoGestion.Controladores;

import com.gestiondeportiva.proyectoGestion.DTOs.CuotaMensualDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.ClaveCuota;
import com.gestiondeportiva.proyectoGestion.Dominio.CuotaMensual;
import com.gestiondeportiva.proyectoGestion.Servicios.ServicioCuotaMensual;
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

    private final ServicioCuotaMensual servicioCuotaMensual;

    @Autowired
    public ControladorCuotaMensual(ServicioCuotaMensual servicioCuotaMensual) {
        this.servicioCuotaMensual = servicioCuotaMensual;
    }

    @PostMapping("/crear")
    public ResponseEntity<CuotaMensualDTO> createOrUpdateCuotaMensual(@Valid @RequestBody CuotaMensualDTO cuotaMensual) {
        CuotaMensualDTO nuevaCuotaMensual = servicioCuotaMensual.createOrUpdate(cuotaMensual);
        return new ResponseEntity<>(nuevaCuotaMensual, HttpStatus.CREATED);
    }

    @GetMapping("/ver")
    public List<CuotaMensualDTO> getAllCuotasMensuales() {
        return servicioCuotaMensual.selectAll();
    }

    @GetMapping("/verId/{anio}/{mes}/{inscr}")
    public ResponseEntity<CuotaMensualDTO> getCuotaMensualById(@PathVariable int anio, @PathVariable int mes, @PathVariable Integer i ) {
        ClaveCuota claveCuota = new ClaveCuota(anio, mes,i);
        CuotaMensualDTO cuotaMensual = servicioCuotaMensual.selectById(claveCuota);

        if (cuotaMensual != null) {
            return new ResponseEntity<>(cuotaMensual, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{anio}/{mes}/{inscr}")
    public ResponseEntity<Void> deleteCuotaMensual(@PathVariable int anio, @PathVariable int mes, @PathVariable Integer i) {
        ClaveCuota claveCuota = new ClaveCuota(anio, mes, i);

        try {
            servicioCuotaMensual.delete(claveCuota);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Otros métodos según sea necesario
}