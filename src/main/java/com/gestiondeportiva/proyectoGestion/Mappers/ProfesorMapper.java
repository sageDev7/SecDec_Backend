package com.gestiondeportiva.proyectoGestion.Mappers;

import com.gestiondeportiva.proyectoGestion.DTOs.ProfesorDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.*;

public class ProfesorMapper {

    public static ProfesorDTO entityToDTO(Profesor p) {
        ProfesorDTO pDto = new ProfesorDTO();
        pDto.setNomyApe(p.getNomyApe());
        pDto.setDni(p.getDni());
        pDto.setDomicilio(p.getDomicilio());
        pDto.setLocalidad(p.getLocalidad());
        pDto.setProvincia(p.getProvincia());
        pDto.setTel(p.getTel());
        pDto.setFecNac(p.getFecNac());
        pDto.setSexo(p.getSexo());
        pDto.setObservaciones(p.getObservaciones());
        pDto.setFecIng(p.getFecIng());
        for (Disciplina d : p.getDisciplinas()) {
            pDto.getDisciplinas().add(d.getId_d());
        }
        pDto.setId_p(p.getId_p());
        return pDto;
    }

    public static Profesor DTOToEntity(ProfesorDTO pDto) {
        Profesor p = new Profesor();
        p.setNomyApe(pDto.getNomyApe());
        p.setDni(pDto.getDni());
        p.setDomicilio(pDto.getDomicilio());
        p.setLocalidad(pDto.getLocalidad());
        p.setProvincia(pDto.getProvincia());
        p.setTel(pDto.getTel());
        p.setFecNac(pDto.getFecNac());
        p.setSexo(pDto.getSexo());
        p.setObservaciones(pDto.getObservaciones());
        p.setFecIng(pDto.getFecIng());
        p.setId_p(pDto.getId_p());
        return p;
    }

}
