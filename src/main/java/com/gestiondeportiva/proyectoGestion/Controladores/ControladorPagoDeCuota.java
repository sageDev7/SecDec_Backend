package com.gestiondeportiva.proyectoGestion.Controladores;

import com.gestiondeportiva.proyectoGestion.Dominio.PagoDeCuota;
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
    public ResponseEntity<PagoDeCuota> createOrUpdatePagoDeCuota(@Valid @RequestBody PagoDeCuota pagoDeCuota) {
        PagoDeCuota nuevoPagoDeCuota = servicioPagoDeCuota.createOrUpdate(pagoDeCuota);
        return new ResponseEntity<>(nuevoPagoDeCuota, HttpStatus.CREATED);
    }

    @GetMapping("/ver")
    public List<PagoDeCuota> getAllPagosDeCuota() {
        return servicioPagoDeCuota.selectAll();
    }

    @GetMapping("/verId/{id}")
    public ResponseEntity<PagoDeCuota> getPagoDeCuotaById(@PathVariable Integer id) {
        PagoDeCuota pagoDeCuota = servicioPagoDeCuota.selectById(id);

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

    // Otros métodos según sea necesario
}
