package com.gestiondeportiva.proyectoGestion.Seguridad;

import com.gestiondeportiva.proyectoGestion.Dominio.Rol;
import com.gestiondeportiva.proyectoGestion.Dominio.Usuario;
import com.gestiondeportiva.proyectoGestion.Persistencia.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    public CustomUserDetailsService(IUsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Collection<GrantedAuthority> mapToAuthorities(List<Rol> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(usuario.getUsername(), usuario.getPassword(), mapToAuthorities(usuario.getRoles()));
    }
}
