package com.gestiondeportiva.proyectoGestion.Mappers;

import com.gestiondeportiva.proyectoGestion.DTOs.AlumnoDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.CuotaMensualDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.InscripcionDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.*;

import java.sql.Date;
import java.util.List;

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

        /*
        for (ClaveCuota cc : aDto.getCuotasMensuales()) {
            CuotaMensual cm = new CuotaMensual();  // Nueva instancia en cada iteración
            cm.setClaveCuota(cc);
            cm.getAlumno().setId_a(aDto.getId_a());
            a.getCuotasMensuales().add(cm);
        }

        for (InscripcionDTO i : aDto.getInscripciones()) {
            a.getInscripciones().add(InscripcionMapper.DTOToEntity(i));
        }
        */

        a.setId_a(aDto.getId_a());
        System.out.println(a.toString());
        return a;
    }
}
