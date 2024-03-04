package com.gestiondeportiva.SAE_Backend.Servicios;

import com.gestiondeportiva.SAE_Backend.DTOs.CuotaMensualDTO;
import com.gestiondeportiva.SAE_Backend.Dominio.*;
import com.gestiondeportiva.SAE_Backend.Mappers.CuotaMensualMapper;
import com.gestiondeportiva.SAE_Backend.Persistencia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioCuotaMensual {

    private final ICuotaMensualRepositorio cuotaMensualRepositorio;
    private final IAlumnoRepositorio alumnoRepositorio;
    private final IDisciplinaRepositorio disciplinaRepositorio;
    private final IInscripcionRepositorio inscripcionRepositorio;
    private final IPagoDeCuotaRepositorio pagoDeCuotaRepositorio;

    @Autowired
    public ServicioCuotaMensual(ICuotaMensualRepositorio cuotaMensualRepositorio, IAlumnoRepositorio alumnoRepositorio, IDisciplinaRepositorio disciplinaRepositorio, IInscripcionRepositorio inscripcionRepositorio, IPagoDeCuotaRepositorio pagoDeCuotaRepositorio) {
        this.cuotaMensualRepositorio = cuotaMensualRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
        this.disciplinaRepositorio = disciplinaRepositorio;
        this.inscripcionRepositorio = inscripcionRepositorio;
        this.pagoDeCuotaRepositorio = pagoDeCuotaRepositorio;
    }

    // Crea o actualiza una cuota mensual en la base de datos.
    public CuotaMensualDTO createOrUpdate(CuotaMensualDTO cm){
        // Mapea el DTO a una entidad de CuotaMensual.
        CuotaMensual nuevaCuota = CuotaMensualMapper.DTOToEntity(cm);

        // Busca y asigna el alumno correspondiente a la cuota mensual.
        Optional<Alumno> a = alumnoRepositorio.findById(cm.getAlumno());
        if (a.isPresent()) {
            nuevaCuota.setAlumno(a.get());
        }

        // Busca y asigna la disciplina correspondiente a la cuota mensual.
        Optional<Disciplina> d = disciplinaRepositorio.findById(cm.getDisciplina());
        if (d.isPresent()) {
            nuevaCuota.setDisciplina(d.get());
        }

        // Busca y asigna la inscripción correspondiente a la cuota mensual.
        Optional<Inscripcion> i = inscripcionRepositorio.findById(cm.getClaveCuota().getIcod());
        if (i.isPresent()) {
            nuevaCuota.setInscripcion(i.get());
        }

        // Busca y asigna el pago de cuota correspondiente a la cuota mensual.
        Optional<PagoDeCuota> pdc;
        if (cm.getPdc()!=null) {
            pdc = pagoDeCuotaRepositorio.findById(cm.getPdc().get().getNroPago());
            nuevaCuota.setPdc(pdc.get());
        }

        // Mapea la entidad actualizada o creada a un DTO y la guarda en la base de datos.
        return CuotaMensualMapper.entityToDTO(cuotaMensualRepositorio.save(nuevaCuota));
    }

    // Obtiene todas las cuotas mensuales de la base de datos.
    public List<CuotaMensualDTO> selectAll() {
        // Lista para almacenar los DTO de las cuotas mensuales.
        List<CuotaMensualDTO> lcDto = new ArrayList<>();

        // Recorre todas las cuotas mensuales y las mapea a DTO.
        for (CuotaMensual cm: cuotaMensualRepositorio.findAll()) {
            lcDto.add(CuotaMensualMapper.entityToDTO(cm));
        }
        return lcDto;
    }

    // Obtiene una cuota mensual por su clave.
    public CuotaMensualDTO selectById(ClaveCuota cc) {
        // Mapea la entidad de la cuota mensual a un DTO.
        return CuotaMensualMapper.entityToDTO(cuotaMensualRepositorio.findById(cc).orElse(null));
    }

    // Elimina una cuota mensual de la base de datos por su clave.
    public void delete(ClaveCuota cc) {
        cuotaMensualRepositorio.deleteById(cc);
    }
}
