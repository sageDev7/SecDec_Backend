package com.gestiondeportiva.SAE_Backend.Seguridad;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Método para iniciar el proceso de autenticación
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Envía un error de no autorizado (401) junto con el mensaje de excepción de autenticación
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}