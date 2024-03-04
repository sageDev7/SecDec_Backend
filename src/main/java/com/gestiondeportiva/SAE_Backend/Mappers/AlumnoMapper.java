package com.gestiondeportiva.SAE_Backend.Mappers;

import com.gestiondeportiva.SAE_Backend.DTOs.AlumnoDTO;
import com.gestiondeportiva.SAE_Backend.Dominio.*;

public class AlumnoMapper {

    public static AlumnoDTO entityToDTO(Alumno a){
        AlumnoDTO aDto = new AlumnoDTO();
        aDto.setNomyApe(a.getNomyApe());
        aDto.setDni(a.getDni());
        aDto.setDomicilio(a.getDomicilio());
        aDto.setLocalidad(a.getLocalidad());
        aDto.setProvincia(a.getProvincia());
        aDto.setTel(a.getTel());
        aDto.setFecNac(a.getFecNac());
        aDto.setSexo(a.getSexo());
        aDto.setObservaciones(a.getObservaciones());
        aDto.setTipoAlumno(a.getTipoAlumno());
        aDto.setTipoSeguro(a.getTipoSeguro());
        for (CuotaMensual cm : a.getCuotasMensuales()){
            aDto.getCuotasMensuales().add(cm.getClaveCuota());
        }
        for (Inscripcion i : a.getInscripciones()){
            aDto.getInscripciones().add(InscripcionMapper.entityToDTO(i));
        }
        aDto.setId_a(a.getId_a());
        return aDto;
    }

    public static Alumno DTOToEntity(AlumnoDTO aDto){
        Alumno a = new Alumno();
        a.setNomyApe(aDto.getNomyApe());
        a.setDni(aDto.getDni());
        a.setDomicilio(aDto.getDomicilio());
        a.setLocalidad(aDto.getLocalidad());
        a.setProvincia(aDto.getProvincia());
        a.setTel(aDto.getTel());
        a.setFecNac(aDto.getFecNac());
        a.setSexo(aDto.getSexo());
        a.setObservaciones(aDto.getObservaciones());
        a.setTipoAlumno(aDto.getTipoAlumno());
        a.setTipoSeguro(aDto.getTipoSeguro());

        a.setId_a(aDto.getId_a());
        return a;
    }
}
