package com.gestiondeportiva.proyectoGestion.Mappers;

import com.gestiondeportiva.proyectoGestion.DTOs.CuotaMensualDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.*;

import java.util.Optional;

public class CuotaMensualMapper {

    public static CuotaMensualDTO entityToDTO (CuotaMensual cm){
        CuotaMensualDTO cmDto = new CuotaMensualDTO();
        cmDto.setClaveCuota(cm.getClaveCuota());
        cmDto.setAlumno(cm.getAlumno().getId_a());
        cmDto.setDisciplina(cm.getDisciplina().getId_d());
        if(cm.getPdc()!=null) {
            cmDto.setPdc(Optional.ofNullable(PagoDeCuotaMapper.entityToDTO(cm.getPdc())));
        }
        cmDto.setImporteCuota(cm.getDisciplina().getImporteCuota());
        return cmDto;
    }

    public static CuotaMensual DTOToEntity(CuotaMensualDTO cmDto) {
        CuotaMensual cm = new CuotaMensual();
        cm.setClaveCuota(cmDto.getClaveCuota());
        return cm;
    }


}
