package com.gestiondeportiva.SAE_Backend.Servicios;

import com.gestiondeportiva.SAE_Backend.DTOs.*;
import com.gestiondeportiva.SAE_Backend.Dominio.*;
import com.gestiondeportiva.SAE_Backend.Mappers.*;
import com.gestiondeportiva.SAE_Backend.Persistencia.*;
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
    public ServicioUsuario(IUsuarioRepositorio usuarioRepositorio, IInscripcionRepositorio inscripcionRepositorio, IRolRepositorio rolRepositorio, IPagoDeCuotaRepositorio pagoDeCuotaRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.inscripcionRepositorio = inscripcionRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.pagoDeCuotaRepositorio = pagoDeCuotaRepositorio;
    }

    // Crea o actualiza un usuario en la base de datos.
    public UsuarioDTO createOrUpdate(UsuarioDTO o){
        Usuario nuevoUsuario = UsuarioMapper.DTOToEntity(o);

        // Asociar inscripciones existentes al nuevo usuario.
        for (Integer icod : o.getInscripciones()) {
            Optional<Inscripcion> i = inscripcionRepositorio.findById(icod);
            if (i.isPresent()) {
                nuevoUsuario.getInscripciones().add(i.get());
            }
        }

        // Asociar roles existentes al nuevo usuario.
        for (RolDTO rDto : o.getRoles()) {
            Optional<Rol> r = rolRepositorio.findById(rDto.getId_r());
            if (r.isPresent()) {
                nuevoUsuario.getRoles().add(r.get());
            }
        }

        // Asociar pagos de cuotas existentes al nuevo usuario.
        for (Integer id_pdc : o.getPagosDeCuotas()) {
            Optional<PagoDeCuota> pdc = pagoDeCuotaRepositorio.findById(id_pdc);
            if (pdc.isPresent()) {
                nuevoUsuario.getPagosDeCuotas().add(pdc.get());
            }
        }

        return UsuarioMapper.entityToDTO(usuarioRepositorio.save(nuevoUsuario));
    }

    // Obtiene todos los usuarios de la base de datos.
    public List<UsuarioDTO> selectAll() {
        List<UsuarioDTO> luDto = new ArrayList<>();
        for (Usuario u: usuarioRepositorio.findAll()) {
            luDto.add(UsuarioMapper.entityToDTO(u));
        }
        return luDto;
    }

    // Obtiene un usuario por su ID.
    public UsuarioDTO selectById(Integer id_o) {
        return UsuarioMapper.entityToDTO(usuarioRepositorio.findById(id_o).orElse(null));
    }

    // Elimina un usuario de la base de datos por su ID.
    public void delete(Integer id_o) {
        usuarioRepositorio.deleteById(id_o);
    }
}