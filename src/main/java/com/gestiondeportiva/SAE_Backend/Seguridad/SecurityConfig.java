package com.gestiondeportiva.SAE_Backend.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    // Configuración de permisos y roles
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(); // Configuración CORS
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()

                // Configuración de permisos para endpoints específicos
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/alumnos/crear").hasAnyAuthority("admin", "cajero")
                .requestMatchers(HttpMethod.DELETE, "/alumnos/eliminar/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.POST, "/cuotas-mensuales/crear").hasAnyAuthority("admin", "cajero")
                .requestMatchers(HttpMethod.DELETE, "/cuotas-mensuales/eliminar/**").hasAnyAuthority("admin", "cajero")
                .requestMatchers(HttpMethod.POST, "/disciplinas/crear").hasAuthority("admin")
                .requestMatchers(HttpMethod.DELETE, "/disciplinas/eliminar/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.POST, "/inscripciones/crear").hasAnyAuthority("admin", "cajero")
                .requestMatchers(HttpMethod.DELETE, "/inscripciones/eliminar/**").hasAnyAuthority("admin", "cajero")
                .requestMatchers(HttpMethod.POST, "/pagos-de-cuota/crear").hasAnyAuthority("admin", "cajero")
                .requestMatchers(HttpMethod.DELETE, "/pagos-de-cuota/eliminar/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.POST, "/profesores/crear").hasAuthority("admin")
                .requestMatchers(HttpMethod.DELETE, "/profesores/eliminar/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.POST, "/usuarios/crear").hasAuthority("admin")
                .requestMatchers(HttpMethod.DELETE, "/usuarios/eliminar/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.GET, "/usuarios").hasAuthority("admin")
                .requestMatchers(HttpMethod.GET, "/usuarios/ver").hasAuthority("admin")
                .requestMatchers(HttpMethod.GET, "/usuarios/verId/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.GET, "/alumnos").hasAnyAuthority("admin", "cajero")
                .requestMatchers(HttpMethod.GET, "/alumnos/ver").hasAnyAuthority("admin", "cajero")
                .requestMatchers(HttpMethod.GET, "/alumnos/verId/**").hasAnyAuthority("admin", "cajero")
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        // Agregar el filtro de autenticación JWT antes del filtro de usuario y contraseña
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // Configuración CORS para permitir solicitudes desde el frontend
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Frontend
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Access-Control-Allow-Origin"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
