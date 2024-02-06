package com.gestiondeportiva.proyectoGestion.Controladores;

import com.gestiondeportiva.proyectoGestion.DTOs.PagoDeCuotaDTO;
import com.gestiondeportiva.proyectoGestion.Servicios.ServicioPagoDeCuota;
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
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorPagoDeCuota {
    private final ServicioPagoDeCuota servicioPagoDeCuota;

    @Autowired
    public ControladorPagoDeCuota(ServicioPagoDeCuota servicioPagoDeCuota) {
        this.servicioPagoDeCuota = servicioPagoDeCuota;
    }

    @PostMapping("/crear")
    public ResponseEntity<PagoDeCuotaDTO> createOrUpdatePagoDeCuota(@Valid @RequestBody PagoDeCuotaDTO pagoDeCuota) {
        PagoDeCuotaDTO nuevoPagoDeCuota = servicioPagoDeCuota.createOrUpdate(pagoDeCuota);
        return new ResponseEntity<>(nuevoPagoDeCuota, HttpStatus.CREATED);
    }

    @GetMapping("/ver")
    public List<PagoDeCuotaDTO> getAllPagosDeCuota() {
        return servicioPagoDeCuota.selectAll();
    }

    @GetMapping("/verId/{id}")
    public ResponseEntity<PagoDeCuotaDTO> getPagoDeCuotaById(@PathVariable Integer id) {
        PagoDeCuotaDTO pagoDeCuota = servicioPagoDeCuota.selectById(id);

        if (pagoDeCuota != null) {
            return new ResponseEntity<>(pagoDeCuota, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deletePagoDeCuota(@PathVariable Integer id) {
        try {
            servicioPagoDeCuota.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}