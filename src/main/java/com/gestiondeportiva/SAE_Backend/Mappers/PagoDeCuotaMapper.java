package com.gestiondeportiva.SAE_Backend.Mappers;

import com.gestiondeportiva.SAE_Backend.DTOs.PagoDeCuotaDTO;
import com.gestiondeportiva.SAE_Backend.Dominio.PagoDeCuota;


public class PagoDeCuotaMapper {
    public static PagoDeCuotaDTO entityToDTO(PagoDeCuota pdc) {
        if (pdc==null)
            return null;
        PagoDeCuotaDTO pdcDto = new PagoDeCuotaDTO();
        pdcDto.setNroPago(pdc.getNroPago());
        pdcDto.setUsuarioNombre(pdc.getUsuario().getUsername());
        pdcDto.setFecha(pdc.getFecha());
        pdcDto.setHora(pdc.getHora());
        pdcDto.setImporte(pdc.getImporte());
        pdcDto.setUsuarioNombre(pdc.getUsuario().getUsername());
        return pdcDto;
    }

    public static PagoDeCuota DTOToEntity(PagoDeCuotaDTO pdcDto) {
        PagoDeCuota r = new PagoDeCuota();
        r.setNroPago(pdcDto.getNroPago());
        r.setFecha(pdcDto.getFecha());
        r.setHora(pdcDto.getHora());
        r.setImporte(pdcDto.getImporte());

        return r;
    }
}
