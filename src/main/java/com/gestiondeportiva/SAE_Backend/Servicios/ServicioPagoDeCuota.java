package com.gestiondeportiva.SAE_Backend.Servicios;

import com.gestiondeportiva.SAE_Backend.DTOs.*;
import com.gestiondeportiva.SAE_Backend.Dominio.*;
import com.gestiondeportiva.SAE_Backend.Mappers.*;
import com.gestiondeportiva.SAE_Backend.Persistencia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioPagoDeCuota {

    private final IPagoDeCuotaRepositorio pagoDeCuotaRepositorio;
    private final IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    public ServicioPagoDeCuota(IPagoDeCuotaRepositorio pagoDeCuotaRepositorio, IUsuarioRepositorio usuarioRepositorio) {
        this.pagoDeCuotaRepositorio = pagoDeCuotaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    // Crea o actualiza un pago de cuota en la base de datos.
    public PagoDeCuotaDTO createOrUpdate(PagoDeCuotaDTO pdc){
        PagoDeCuota nuevoPago = PagoDeCuotaMapper.DTOToEntity(pdc);

        // Asociar usuario existente al nuevo pago de cuota.
        Optional<Usuario> u = usuarioRepositorio.findByUsername(pdc.getUsuarioNombre());
        if (u.isPresent()) {
            nuevoPago.setUsuario(u.get());
        }
        return PagoDeCuotaMapper.entityToDTO(pagoDeCuotaRepositorio.save(nuevoPago));
    }

    // Obtiene todos los pagos de cuota de la base de datos.
    public List<PagoDeCuotaDTO> selectAll() {
        List<PagoDeCuotaDTO> lpDto = new ArrayList<>();
        for (PagoDeCuota pdc: pagoDeCuotaRepositorio.findAll()) {
            lpDto.add(PagoDeCuotaMapper.entityToDTO(pdc));
        }
        return lpDto;
    }

    // Obtiene un pago de cuota por su ID.
    public PagoDeCuotaDTO selectById(Integer id_pdc) {
        return PagoDeCuotaMapper.entityToDTO(pagoDeCuotaRepositorio.findById(id_pdc).orElse(null));
    }

    // Elimina un pago de cuota de la base de datos por su ID.
    public void delete(Integer id_pdc) {
        pagoDeCuotaRepositorio.deleteById(id_pdc);
    }
}
