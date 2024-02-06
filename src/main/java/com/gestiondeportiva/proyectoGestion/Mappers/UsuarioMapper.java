package com.gestiondeportiva.proyectoGestion.Mappers;

import com.gestiondeportiva.proyectoGestion.DTOs.UsuarioDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.*;

public class UsuarioMapper {
    public static UsuarioDTO entityToDTO(Usuario u) {
        UsuarioDTO uDto = new UsuarioDTO();
        uDto.setUsername(u.getUsername());
        uDto.setId_u(u.getId_u());
        for (Rol r : u.getRoles()) {
            uDto.getRoles().add(RolMapper.entityToDTO(r));
        }
        for (PagoDeCuota pdc : u.getPagosDeCuotas()) {
            uDto.getPagosDeCuotas().add(pdc.getNroPago());
        }
        for (Inscripcion i : u.getInscripciones()) {
            uDto.getInscripciones().add(i.getIcod());
        }
        return uDto;
    }

    public static Usuario DTOToEntity(UsuarioDTO uDto) {
        Usuario u = new Usuario();
        u.setId_u(uDto.getId_u());
        u.setUsername(uDto.getUsername());
        return u;
    }
}
