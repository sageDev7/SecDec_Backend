package com.gestiondeportiva.proyectoGestion.Mappers;

import com.gestiondeportiva.proyectoGestion.DTOs.DisciplinaDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.*;


public class DisciplinaMapper {

    public static DisciplinaDTO entityToDTO (Disciplina d){
        DisciplinaDTO dDto = new DisciplinaDTO();
        dDto.setNombre(d.getNombre());
        dDto.setImporte(d.getImporte());
        dDto.setImporteCuota(d.getImporteCuota());
        for (Inscripcion i : d.getInscripciones()){
            dDto.getInscripciones().add(InscripcionMapper.entityToDTO(i));
        }
        for (CuotaMensual cm : d.getCuotasMensuales()){
            dDto.getCuotasMensuales().add(cm.getClaveCuota());
        }
        for (Profesor p : d.getProfesores()){
            dDto.getProfesores().add(ProfesorMapper.entityToDTO(p));
        }
        dDto.setId_d(d.getId_d());
        return dDto;
    }

    public static Disciplina DTOToEntity(DisciplinaDTO dDto) {
        Disciplina d = new Disciplina();
        d.setNombre(dDto.getNombre());
        d.setImporte(dDto.getImporte());
        d.setImporteCuota(dDto.getImporteCuota());

        d.setId_d(dDto.getId_d());
        return d;
    }

}
