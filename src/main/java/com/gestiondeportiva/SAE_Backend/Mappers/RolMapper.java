package com.gestiondeportiva.SAE_Backend.Mappers;

import com.gestiondeportiva.SAE_Backend.DTOs.RolDTO;
import com.gestiondeportiva.SAE_Backend.Dominio.Rol;
import com.gestiondeportiva.SAE_Backend.Dominio.Usuario;

public class RolMapper {
    public static RolDTO entityToDTO(Rol r) {
        RolDTO rDto = new RolDTO();
        rDto.setNombre(r.getNombre());
        rDto.setId_r(r.getId_r());
        for (Usuario u : r.getUsuarios()) {
            rDto.getUsuarios().add(u.getId_u());
        }
        return rDto;
    }

    public static Rol DTOToEntity(RolDTO rDto) {
        Rol r = new Rol();
        r.setId_r(rDto.getId_r());
        r.setNombre(rDto.getNombre());

        return r;
    }
}
