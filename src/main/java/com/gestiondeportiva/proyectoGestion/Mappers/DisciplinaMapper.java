package com.gestiondeportiva.proyectoGestion.Mappers;

import com.gestiondeportiva.proyectoGestion.DTOs.CuotaMensualDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.DisciplinaDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.InscripcionDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.ProfesorDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.*;

import java.util.List;

public class DisciplinaMapper {
    private Integer id_d;
    private String nombre;
    private float importe;
    private float importeCuota;
    private List<InscripcionDTO> inscripciones;
    private List<ClaveCuota> cuotasMensuales;
    private List<ProfesorDTO> profesores;

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

        /*
        for (InscripcionDTO iDto : dDto.getInscripciones()) {
            d.getInscripciones().add(InscripcionMapper.DTOToEntity(iDto));
        }

        for (ClaveCuota cc : dDto.getCuotasMensuales()) {
            CuotaMensual cm = new CuotaMensual();
            cm.setClaveCuota(cc);
            d.getCuotasMensuales().add(cm);
        }

        for (ProfesorDTO pDto : dDto.getProfesores()) {
            d.getProfesores().add(ProfesorMapper.DTOToEntity(pDto));
        }
         */

        d.setId_d(dDto.getId_d());
        return d;
    }

}
