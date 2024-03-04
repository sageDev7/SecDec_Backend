package com.gestiondeportiva.SAE_Backend.Dominio;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;

@Entity
@Table(name = "PagosCuotasMensuales")
@Data
@NoArgsConstructor
public class PagoDeCuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nroPago;
    @Column
    private Date fecha;
    @Column
    private Time hora;
    @Column
    private float importe;
    @ManyToOne
    private Usuario usuario = new Usuario();
}
