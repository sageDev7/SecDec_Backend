package com.gestiondeportiva.proyectoGestion.Servicios;

import com.gestiondeportiva.proyectoGestion.DTOs.InscripcionDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.ProfesorDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.RolDTO;
import com.gestiondeportiva.proyectoGestion.DTOs.UsuarioDTO;
import com.gestiondeportiva.proyectoGestion.Dominio.*;
import com.gestiondeportiva.proyectoGestion.Mappers.ProfesorMapper;
import com.gestiondeportiva.proyectoGestion.Mappers.UsuarioMapper;
import com.gestiondeportiva.proyectoGestion.Persistencia.IInscripcionRepositorio;
import com.gestiondeportiva.proyectoGestion.Persistencia.IPagoDeCuotaRepositorio;
import com.gestiondeportiva.proyectoGestion.Persistencia.IRolRepositorio;
import com.gestiondeportiva.proyectoGestion.Persistencia.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuario {

    private final IUsuarioRepositorio usuarioRepositorio;
    private final IInscripcionRepositorio inscripcionRepositorio;
    private final IRolRepositorio rolRepositorio;
    private final IPagoDeCuotaRepositorio pagoDeCuotaRepositorio;

    @Autowired
    public ServicioUsuario(IUsuarioRepositorio usuarioRepositorio, IInscripcionRepositorio inscripcionRepositorio,IRolRepositorio rolRepositorio,IPagoDeCuotaRepositorio pagoDeCuotaRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.inscripcionRepositorio = inscripcionRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.pagoDeCuotaRepositorio = pagoDeCuotaRepositorio;
    }

    public UsuarioDTO createOrUpdate(UsuarioDTO o){
        Usuario nuevoUsuario = UsuarioMapper.DTOToEntity(o);

        for (Integer icod : o.getInscripciones()) {
            Optional<Inscripcion> i = inscripcionRepositorio.findById(icod);
            if (i.isPresent()) {
                nuevoUsuario.getInscripciones().add(i.get());
            }
        }
        for (RolDTO rDto : o.getRoles()) {
            Optional<Rol> r = rolRepositorio.findById(rDto.getId_r());
            if (r.isPresent()) {
                nuevoUsuario.getRoles().add(r.get());
            }
        }
        for (Integer id_pdc : o.getPagosDeCuotas()) {
            Optional<PagoDeCuota> pdc = pagoDeCuotaRepositorio.findById(id_pdc);
            if (pdc.isPresent()) {
                nuevoUsuario.getPagosDeCuotas().add(pdc.get());
            }
        }
        return UsuarioMapper.entityToDTO(usuarioRepositorio.save(nuevoUsuario));
    }

    public List<UsuarioDTO> selectAll() {
        List<UsuarioDTO> luDto = new ArrayList<>();
        for (Usuario u: usuarioRepositorio.findAll()) {
            luDto.add(UsuarioMapper.entityToDTO(u));
        }
        return luDto;
    }

    public UsuarioDTO selectById(Integer id_o) {
        return UsuarioMapper.entityToDTO(usuarioRepositorio.findById(id_o).orElse(null));
    }

    public void delete(Integer id_o) {
        usuarioRepositorio.deleteById(id_o);
    }

    // Otros métodos según sea necesario
}