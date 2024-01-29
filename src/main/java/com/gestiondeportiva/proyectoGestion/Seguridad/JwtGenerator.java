package com.gestiondeportiva.proyectoGestion.Seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {
    //Generar token a traves de la autenticacion
    public String generateToken(Authentication auth){
        String username = auth.getName();
        Date actTime = new Date();
        Date expToken = new Date(actTime.getTime() + SecurityConstants.JWT_EXPIRATION_TOKEN);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expToken)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_FIRMA)
                .compact();
        return token;
    }

    //Extraer username a partir de un token
    public String getJwtUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_FIRMA)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    //Validar token
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_FIRMA).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Jwt expired or incorrect");
        }
    }

}
