package com.gestiondeportiva.proyectoGestion.Servicios;

import com.gestiondeportiva.proyectoGestion.Dominio.PagoDeCuota;
import com.gestiondeportiva.proyectoGestion.Persistencia.IPagoDeCuotaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPagoDeCuota {

    private final IPagoDeCuotaRepositorio pagoDeCuotaRepositorio;

    @Autowired
    public ServicioPagoDeCuota(IPagoDeCuotaRepositorio pagoDeCuotaRepositorio) {
        this.pagoDeCuotaRepositorio = pagoDeCuotaRepositorio;
    }

    public PagoDeCuota createOrUpdate(PagoDeCuota pdc){
        return pagoDeCuotaRepositorio.save(pdc);
    }

    public List<PagoDeCuota> selectAll() {
        return pagoDeCuotaRepositorio.findAll();
    }

    public PagoDeCuota selectById(Integer id_pdc) {
        return pagoDeCuotaRepositorio.findById(id_pdc).orElse(null);
    }

    public void delete(Integer id_pdc) {
        pagoDeCuotaRepositorio.deleteById(id_pdc);
    }

    // Otros métodos según sea necesario
}