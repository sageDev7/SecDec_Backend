package com.gestiondeportiva.SAE_Backend.Mappers;

import com.gestiondeportiva.SAE_Backend.DTOs.DisciplinaDTO;
import com.gestiondeportiva.SAE_Backend.Dominio.*;


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
