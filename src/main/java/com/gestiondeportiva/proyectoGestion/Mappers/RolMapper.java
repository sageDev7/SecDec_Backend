package com.gestiondeportiva.proyectoGestion.Mappers;

import com.gestiondeportiva.proyectoGestion.DTOs.RolDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.Rol;
import com.gestiondeportiva.proyectoGestion.Dominio.Usuario;

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
