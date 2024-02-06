package com.gestiondeportiva.proyectoGestion.Mappers;

import com.gestiondeportiva.proyectoGestion.DTOs.*;
import com.gestiondeportiva.proyectoGestion.Dominio.*;

public class InscripcionMapper {

    public static InscripcionDTO entityToDTO (Inscripcion i){
        InscripcionDTO iDto = new InscripcionDTO();
        iDto.setIcod(i.getIcod());
        iDto.setFecInsc(i.getFecInsc());
        iDto.setFecVenc(i.getFecVenc());
        iDto.setImporteUnico(i.getImporteUnico());
        iDto.setImporteCuotas(i.getDisciplina().getImporteCuota());
        iDto.setTipoInscr(i.getTipoInscr());
        iDto.setAlumno(i.getAlumno().getId_a());
        iDto.setAlumnoNombre(i.getAlumno().getNomyApe());
        iDto.setDisciplinaNombre(i.getDisciplina().getNombre());
        iDto.setDisciplina(i.getDisciplina().getId_d());
        if(i.getUsuario()!=null)
            iDto.setUsuarioNombre(i.getUsuario().getUsername());
        for (CuotaMensual cm: i.getCuotasMensuales()) {
            iDto.getCuotasMensuales().add(CuotaMensualMapper.entityToDTO(cm));
        };
        return iDto;
    }

    public static Inscripcion DTOToEntity(InscripcionDTO iDto) {
        Inscripcion i = new Inscripcion();
        i.setIcod(iDto.getIcod());
        i.setFecInsc(iDto.getFecInsc());
        i.setFecVenc(iDto.getFecVenc());
        i.setImporteUnico(iDto.getImporteUnico());
        i.setTipoInscr(iDto.getTipoInscr());

        /*
        if (iDto.getAlumno() != null) {
            Alumno alumno = new Alumno();
            alumno.setId_a(iDto.getAlumno());
            i.setAlumno(alumno);
        }

        if (iDto.getDisciplina() != null) {
            Disciplina disciplina = new Disciplina();
            disciplina.setId_d(iDto.getDisciplina());
            i.setDisciplina(disciplina);
        }

        if (iDto.getUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setId_u(iDto.getUsuario());
            i.setUsuario(usuario);
        }

        for (CuotaMensualDTO cmDto : iDto.getCuotasMensuales()) {
            i.getCuotasMensuales().add(CuotaMensualMapper.DTOToEntity(cmDto));
        }
         */
        return i;
    }


}
