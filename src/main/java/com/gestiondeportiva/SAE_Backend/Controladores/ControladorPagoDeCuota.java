package com.gestiondeportiva.SAE_Backend.Controladores;

import com.gestiondeportiva.SAE_Backend.DTOs.PagoDeCuotaDTO;
import com.gestiondeportiva.SAE_Backend.Servicios.ServicioPagoDeCuota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pagos")
@Validated
@CrossOrigin(origins = "http://localhost:4200/") // Origen permitido para CORS
public class ControladorPagoDeCuota {

    // Inyección de dependencias
    private final ServicioPagoDeCuota servicioPagoDeCuota;

    @Autowired
    public ControladorPagoDeCuota(ServicioPagoDeCuota servicioPagoDeCuota) {
        this.servicioPagoDeCuota = servicioPagoDeCuota;
    }

    // Endpoint para crear o actualizar un pago de cuota
    @PostMapping("/crear")
    public ResponseEntity<PagoDeCuotaDTO> createOrUpdatePagoDeCuota(@Valid @RequestBody PagoDeCuotaDTO pagoDeCuota) {
        // Llamada al servicio para crear o actualizar el pago de cuota
        PagoDeCuotaDTO nuevoPagoDeCuota = servicioPagoDeCuota.createOrUpdate(pagoDeCuota);
        // Se devuelve el nuevo pago de cuota creado y se devuelve un estado HTTP 201 (CREATED)
        return new ResponseEntity<>(nuevoPagoDeCuota, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los pagos de cuota
    @GetMapping("/ver")
    public List<PagoDeCuotaDTO> getAllPagosDeCuota() {
        // Llamada al servicio para obtener todos los pagos de cuota
        return servicioPagoDeCuota.selectAll();
    }

    // Endpoint para obtener un pago de cuota por su ID
    @GetMapping("/verId/{id}")
    public ResponseEntity<PagoDeCuotaDTO> getPagoDeCuotaById(@PathVariable Integer id) {
        // Llamada al servicio para obtener un pago de cuota por su ID
        PagoDeCuotaDTO pagoDeCuota = servicioPagoDeCuota.selectById(id);

        if (pagoDeCuota != null) {
            // Si se encuentra el pago de cuota, se devuelve junto con un estado HTTP 200 (OK)
            return new ResponseEntity<>(pagoDeCuota, HttpStatus.OK);
        } else {
            // Si el pago de cuota no se encuentra, se devuelve un estado HTTP 404 (NOT FOUND)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un pago de cuota por su ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deletePagoDeCuota(@PathVariable Integer id) {
        try {
            // Se intenta eliminar el pago de cuota llamando al servicio correspondiente
            servicioPagoDeCuota.delete(id);
            // Si la eliminación tiene éxito, se devuelve un estado HTTP 204 (NO CONTENT)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Si hay un error durante la eliminación, se devuelve un estado HTTP 500 (INTERNAL SERVER ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
