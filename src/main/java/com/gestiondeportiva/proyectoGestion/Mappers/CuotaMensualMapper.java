package com.gestiondeportiva.proyectoGestion.Mappers;

import com.gestiondeportiva.proyectoGestion.DTOs.CuotaMensualDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.DisciplinaDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.*;

public class CuotaMensualMapper {
    private ClaveCuota claveCuota;
    private Integer alumno;
    private Integer disciplina;
    private PagoDeCuota pdc;

    public static CuotaMensualDTO entityToDTO (CuotaMensual cm){
        CuotaMensualDTO cmDto = new CuotaMensualDTO();
        cmDto.setClaveCuota(cm.getClaveCuota());
        cmDto.setAlumno(cm.getAlumno().getId_a());
        cmDto.setDisciplina(cm.getDisciplina().getId_d());
        cmDto.setPdc(cm.getPdc());
        cmDto.setImporteCuota(cm.getDisciplina().getImporteCuota());
        return cmDto;
    }

    public static CuotaMensual DTOToEntity(CuotaMensualDTO cmDto) {
        CuotaMensual cm = new CuotaMensual();
        cm.setClaveCuota(cmDto.getClaveCuota());

        /*
        if (cmDto.getAlumno() != null) {
            Alumno alumno = new Alumno();
            alumno.setId_a(cmDto.getAlumno());
            cm.setAlumno(alumno);
        }

        if (cmDto.getDisciplina() != null) {
            Disciplina disciplina = new Disciplina();
            disciplina.setId_d(cmDto.getDisciplina());
            cm.setDisciplina(disciplina);
        }
        */

        cm.setPdc(cmDto.getPdc());
        return cm;
    }


}
