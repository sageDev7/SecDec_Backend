package com.gestiondeportiva.SAE_Backend.Seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtGenerator {

    // Generar un token a través de la autenticación
    public String generateToken(Authentication auth) {
        String username = auth.getName();
        Date actTime = new Date();
        Date expToken = new Date(actTime.getTime() + SecurityConstants.JWT_EXPIRATION_TOKEN);

        // Crear el token con el nombre de usuario, fecha de emisión y fecha de vencimiento
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expToken)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_FIRMA)
                .compact();
        return token;
    }

    // Extraer el nombre de usuario a partir de un token
    public String getJwtUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_FIRMA)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Validar la autenticidad y vigencia del token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_FIRMA).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Lanzar una excepción si el token ha expirado o es incorrecto
            throw new AuthenticationCredentialsNotFoundException("Jwt expired or incorrect");
        }
    }
}
