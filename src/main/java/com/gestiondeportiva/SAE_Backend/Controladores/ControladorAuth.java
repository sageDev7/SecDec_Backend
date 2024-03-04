package com.gestiondeportiva.SAE_Backend.Controladores;

import com.gestiondeportiva.SAE_Backend.DTOs.*;
import com.gestiondeportiva.SAE_Backend.Dominio.*;
import com.gestiondeportiva.SAE_Backend.Persistencia.*;
import com.gestiondeportiva.SAE_Backend.Seguridad.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/auth/")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAuth {

    // Inyección de dependencias
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private IRolRepositorio rolRepositorio;
    private IUsuarioRepositorio usuarioRepositorio;
    private JwtGenerator jwtGenerator;

    @Autowired
    public ControladorAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IRolRepositorio rolRepositorio, IUsuarioRepositorio usuarioRepositorio, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.rolRepositorio = rolRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.jwtGenerator = jwtGenerator;
    }

    // Endpoint para registrar un cajero
    @PostMapping("registrarCajero")
    public ResponseEntity<String> registrarCajero(@RequestBody RegistroDTO dtoRegistro) {
        // Verifica si el nombre de usuario ya existe
        if (usuarioRepositorio.existsByUsername(dtoRegistro.getUsername())) {
            return new ResponseEntity<>("El usuario ya existe.", HttpStatus.BAD_REQUEST);
        }

        // Crear nuevo usuario y asignarle el rol de cajero
        Usuario usuario = new Usuario();
        usuario.setUsername(dtoRegistro.getUsername());
        usuario.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Rol rol = rolRepositorio.findByNombre("cajero").get();
        usuario.setRoles(Collections.singletonList(rol));
        usuarioRepositorio.save(usuario);

        return new ResponseEntity<>("Registro exitoso.", HttpStatus.OK);
    }

    // Endpoint para registrar un administrador
    @PostMapping("registrarAdmin")
    public ResponseEntity<String> registrarAdmin(@RequestBody RegistroDTO dtoRegistro) {
        // Verifica si el nombre de usuario ya existe
        if (usuarioRepositorio.existsByUsername(dtoRegistro.getUsername())) {
            return new ResponseEntity<>("El usuario ya existe.", HttpStatus.BAD_REQUEST);
        }

        // Crear nuevo usuario y asignarle el rol de administrador
        Usuario usuario = new Usuario();
        usuario.setUsername(dtoRegistro.getUsername());
        usuario.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Rol rol = rolRepositorio.findByNombre("admin").get();
        usuario.setRoles(Collections.singletonList(rol));
        usuarioRepositorio.save(usuario);

        return new ResponseEntity<>("Registro exitoso.", HttpStatus.OK);
    }

    // Endpoint para realizar el login y obtener un token JWT
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dtoLogin) {
        // Autenticación del usuario utilizando el AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoLogin.getUsername(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Obtener roles y ID del usuario
        List<String> roles = new ArrayList<>();
        Integer id_u = -1;
        Optional<Usuario> u = usuarioRepositorio.findByUsername(dtoLogin.getUsername());
        if (u.isPresent()) {
            for (Rol r : u.get().getRoles()) {
                roles.add(r.getNombre());
            }
            id_u = u.get().getId_u();
        }

        // Generar un token JWT y devolver la respuesta con el token y la información del usuario
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token, roles, dtoLogin.getUsername(), id_u), HttpStatus.OK);
    }
}

