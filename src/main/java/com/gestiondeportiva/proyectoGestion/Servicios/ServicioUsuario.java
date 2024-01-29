package com.gestiondeportiva.proyectoGestion.Servicios;

import com.gestiondeportiva.proyectoGestion.Dominio.Usuario;
import com.gestiondeportiva.proyectoGestion.Persistencia.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuario {

    private final IUsuarioRepositorio servicioRepositorio;

    @Autowired
    public ServicioUsuario(IUsuarioRepositorio servicioRepositorio) {
        this.servicioRepositorio = servicioRepositorio;
    }

    public Usuario createOrUpdate(Usuario o){
        return servicioRepositorio.save(o);
    }

    public List<Usuario> selectAll() {
        return servicioRepositorio.findAll();
    }

    public Usuario selectById(Integer id_o) {
        return servicioRepositorio.findById(id_o).orElse(null);
    }

    public void delete(Integer id_o) {
        servicioRepositorio.deleteById(id_o);
    }

    // Otros métodos según sea necesario
}