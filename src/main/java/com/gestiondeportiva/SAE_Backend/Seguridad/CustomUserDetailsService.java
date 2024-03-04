package com.gestiondeportiva.SAE_Backend.Seguridad;

import com.gestiondeportiva.SAE_Backend.Dominio.Rol;
import com.gestiondeportiva.SAE_Backend.Dominio.Usuario;
import com.gestiondeportiva.SAE_Backend.Persistencia.IUsuarioRepositorio;
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

    // Método para mapear roles a authorities
    public Collection<GrantedAuthority> mapToAuthorities(List<Rol> roles){
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre())) // Mapea los roles a objetos GrantedAuthority
                .collect(Collectors.toList()); // Recopila los authorities en una lista
    }

    // Implementación del método loadUserByUsername de la interfaz UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca al usuario por su nombre de usuario en el repositorio de usuarios
        Usuario usuario = usuarioRepositorio.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")); // Lanza una excepción si el usuario no se encuentra
        // Crea y devuelve un objeto UserDetails con el nombre de usuario, la contraseña y los roles convertidos en authorities
        return new User(usuario.getUsername(), usuario.getPassword(), mapToAuthorities(usuario.getRoles()));
    }
}