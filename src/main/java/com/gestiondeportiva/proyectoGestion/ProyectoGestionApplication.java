package com.gestiondeportiva.proyectoGestion;

import com.gestiondeportiva.proyectoGestion.Controladores.ControladorAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class ProyectoGestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoGestionApplication.class, args);
	}
}
