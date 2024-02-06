package com.gestiondeportiva.proyectoGestion.Controladores;

import com.gestiondeportiva.proyectoGestion.DTOs.*;
import com.gestiondeportiva.proyectoGestion.Dominio.*;
import com.gestiondeportiva.proyectoGestion.Persistencia.*;
import com.gestiondeportiva.proyectoGestion.Seguridad.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
@RequestMapping("/auth/")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAuth {
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

    @PostMapping("registrarCajero")
    public ResponseEntity<String> registrarCajero(@RequestBody RegistroDTO dtoRegistro){
        if (usuarioRepositorio.existsByUsername(dtoRegistro.getUsername())){
            return new ResponseEntity<>("El usuario ya existe.", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(dtoRegistro.getUsername());
        usuario.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Rol rol = rolRepositorio.findByNombre("cajero").get();
        usuario.setRoles(Collections.singletonList(rol));
        usuarioRepositorio.save(usuario);
        return new ResponseEntity<>("Registro exitoso.",HttpStatus.OK);
    }

    @PostMapping("registrarAdmin")
    public ResponseEntity<String> registrarAdmin(@RequestBody RegistroDTO dtoRegistro){
        if (usuarioRepositorio.existsByUsername(dtoRegistro.getUsername())){
            return new ResponseEntity<>("El usuario ya existe.", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(dtoRegistro.getUsername());
        usuario.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Rol rol = rolRepositorio.findByNombre("admin").get();
        usuario.setRoles(Collections.singletonList(rol));
        usuarioRepositorio.save(usuario);
        return new ResponseEntity<>("Registro exitoso.",HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dtoLogin){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoLogin.getUsername(),dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        List<String> roles = new ArrayList<>();
        Optional<Usuario> u = usuarioRepositorio.findByUsername(dtoLogin.getUsername());
        if(u.isPresent()){
            for (Rol r : u.get().getRoles()) {
                roles.add(r.getNombre());
            }
        }
        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponseDTO(token,roles, dtoLogin.getUsername()),HttpStatus.OK);
    }
}
