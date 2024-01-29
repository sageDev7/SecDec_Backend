package com.gestiondeportiva.proyectoGestion.Dominio;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CuotasMensuales")
@Data
@NoArgsConstructor
public class CuotaMensual {
    @EmbeddedId
    private ClaveCuota claveCuota;
    @ManyToOne
    private Alumno alumno = new Alumno();
    @ManyToOne
    private Disciplina disciplina = new Disciplina();
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "icod", insertable = false, updatable = false)
    private Inscripcion inscripcion = new Inscripcion();
    @OneToOne
    @JoinColumn(name = "pago_nro_pago")
    private PagoDeCuota pdc;
}
